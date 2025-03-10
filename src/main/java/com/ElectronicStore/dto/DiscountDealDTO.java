package com.ElectronicStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DiscountDealDTO {
    private Long id;
    private String description;
    private double discountPercentage;
    private Integer requiredQuantity;
    private String discountType;
}