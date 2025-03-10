package com.ElectronicStore.controller;

import com.ElectronicStore.dto.DiscountDealDTO;
import com.ElectronicStore.service.DiscountDealService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discounts")
@AllArgsConstructor
public class DiscountController {

    private DiscountDealService discountDealService;

    @PostMapping
    public ResponseEntity<DiscountDealDTO> createDiscountDeal(
            @RequestBody DiscountDealDTO discountDealDTO) {
        DiscountDealDTO discountDeal = discountDealService.createAndSaveDiscountDeal(discountDealDTO);
        return ResponseEntity.ok(discountDeal);
    }
}