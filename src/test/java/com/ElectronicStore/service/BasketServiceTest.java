package com.ElectronicStore.service;


import com.ElectronicStore.dto.BasketDTO;
import com.ElectronicStore.dto.BasketItemDTO;
import com.ElectronicStore.dto.ProductDTO;
import com.ElectronicStore.entity.Account;
import com.ElectronicStore.entity.Basket;
import com.ElectronicStore.entity.BasketItem;
import com.ElectronicStore.entity.Product;
import com.ElectronicStore.repositories.BasketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BasketServiceTest {

    @InjectMocks
    private BasketService basketService;

    @Mock
    private BasketRepository basketRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setupSecurityContext() {
        MockitoAnnotations.openMocks(this);
        Account account = new Account();
        account.setId(1L);
        when(accountService.getCurrentAccount()).thenReturn(account);
    }

    @Test
    public void testAddProductToBasket() {
        BasketItemDTO basketItemDTO = new BasketItemDTO();
        basketItemDTO.setProductId(1L);
        basketItemDTO.setQuantity(2);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Test Product");
        productDTO.setPrice(100.0);

        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(modelMapper.map(productDTO, Product.class));
        basketItem.setQuantity(basketItemDTO.getQuantity());

        Basket basket = new Basket();
        basket.setId(1L);

        Account account = new Account();
        account.setUsername("testuser");
        account.setPassword("password");

        BasketDTO basketDTO = new BasketDTO();
        basketDTO.setId(1L);

        when(basketRepository.save(any(Basket.class))).thenReturn(basket);
        when(basketRepository.findByAccountId(any())).thenReturn(Optional.of(basket));
        when(modelMapper.map(basketItemDTO, BasketItem.class)).thenReturn(basketItem);
        when(modelMapper.map(basket, BasketDTO.class)).thenReturn(basketDTO);

        BasketDTO result = basketService.addProductToBasket(basketItemDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testRemoveProductFromBasket() {
        Basket basket = new Basket();
        basket.setId(1L);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Test Product");
        productDTO.setPrice(100.0);

        Product product = new Product();
        product.setId(1L);

        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(product);
        basketItem.setQuantity(10);
        Set<BasketItem> basketItemSet = new HashSet<>();
        basketItemSet.add(basketItem);
        basket.setItems(basketItemSet);
        BasketDTO basketDTO = new BasketDTO();
        basketDTO.setId(1L);

        when(basketRepository.findByAccountId(any())).thenReturn(Optional.of(basket));
        when(basketRepository.findById(1L)).thenReturn(Optional.of(basket));
        when(basketRepository.save(any())).thenReturn(basket);
        when(modelMapper.map(basket, BasketDTO.class)).thenReturn(basketDTO);
        BasketDTO result = basketService.removeProductFromBasket(1L, 1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testCalculateReceipt() {
        Basket basket = new Basket();
        basket.setId(1L);

        when(basketRepository.findById(1L)).thenReturn(Optional.of(basket));

        String receipt = basketService.calculateReceipt(1L);

        assertNotNull(receipt);
    }
}