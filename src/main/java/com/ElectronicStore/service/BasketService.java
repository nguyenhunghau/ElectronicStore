package com.ElectronicStore.service;

import com.ElectronicStore.dto.BasketDTO;
import com.ElectronicStore.dto.BasketItemDTO;
import com.ElectronicStore.entity.Account;
import com.ElectronicStore.entity.Basket;
import com.ElectronicStore.entity.BasketItem;
import com.ElectronicStore.entity.DiscountDeal;
import com.ElectronicStore.repositories.BasketRepository;
import com.ElectronicStore.service.discount.DiscountDealFactory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BasketService {

    private BasketRepository basketRepository;

    private AccountService accountService;

    private ModelMapper modelMapper;

    public BasketDTO addProductToBasket(BasketItemDTO basketItemDTO) {
        Account account = accountService.getCurrentAccount();
        Optional<Basket> basketOptional = basketRepository.findByAccountId(account.getId());
        Basket basket = basketOptional.orElseGet(Basket::new);
        basket.setAccount(account);
        BasketItem basketItem = modelMapper.map(basketItemDTO, BasketItem.class);
        basket.getItems().add(basketItem);
        Basket updatedBasket = basketRepository.save(basket);
        return modelMapper.map(updatedBasket, BasketDTO.class);
    }

    public BasketDTO removeProductFromBasket(Long basketId, Long productId) {
        Account account = accountService.getCurrentAccount();
        Basket basket = basketRepository.findByAccountId(account.getId()).orElseThrow(() -> new RuntimeException("Basket not found"));
        basket.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        Basket updatedBasket = basketRepository.save(basket);
        return modelMapper.map(updatedBasket, BasketDTO.class);
    }

    public String calculateReceipt(Long basketId) {
        Basket basket = basketRepository.findById(basketId).orElseThrow(() -> new RuntimeException("Basket not found"));
        double total = 0.0;
        StringBuilder receipt = new StringBuilder("Receipt:\n");
        List<BasketItem> items = new ArrayList<>(basket.getItems());
        items.sort(Comparator.comparing(item -> item.getProduct().getId()));

        for (BasketItem item : items) {
            DiscountDeal deal = item.getProduct().getDiscountDeal();
            double itemTotal = item.getProduct().getPrice() * item.getQuantity();
            if(deal != null) {
                itemTotal = DiscountDealFactory.getDiscountService(deal.getDiscountType()).applyDiscount(item, deal);
            }

            receipt.append(item.getProduct().getName())
                    .append(" x ").append(item.getQuantity())
                    .append(" = ").append(itemTotal).append("\n");
            total += itemTotal;
        }

        receipt.append("Total: ").append(total);
        return receipt.toString();
    }
}