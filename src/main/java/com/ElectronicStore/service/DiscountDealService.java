package com.ElectronicStore.service;


import com.ElectronicStore.dto.DiscountDealDTO;
import com.ElectronicStore.entity.DiscountDeal;
import com.ElectronicStore.repositories.DiscountDealRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiscountDealService {

    private final DiscountDealRepository discountDealRepository;
    private final ModelMapper modelMapper;

    public DiscountDealDTO createAndSaveDiscountDeal(DiscountDealDTO discountDealDTO) {
        DiscountDeal discountDeal = modelMapper.map(discountDealDTO, DiscountDeal.class);
        DiscountDeal savedDiscountDeal = discountDealRepository.save(discountDeal);
        return modelMapper.map(savedDiscountDeal, DiscountDealDTO.class);
    }

    public DiscountDealDTO getDiscountDeal(Long id) {
        DiscountDeal discountDeal = discountDealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discount deal not found"));
        return modelMapper.map(discountDeal, DiscountDealDTO.class);
    }
}