package com.ElectronicStore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BasketDTO {
    private Long id;
    private Long accountId;
    private List<BasketItemDTO> items;
    private double totalPrice;
}