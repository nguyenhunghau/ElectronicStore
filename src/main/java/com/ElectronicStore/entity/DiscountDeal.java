package com.ElectronicStore.entity;

import com.ElectronicStore.dto.DiscountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DiscountDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private double discountPercentage;
    private Integer requiredQuantity;
    private double requiredPrice;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
}