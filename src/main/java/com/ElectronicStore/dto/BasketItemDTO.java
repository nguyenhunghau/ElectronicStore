package com.ElectronicStore.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BasketItemDTO {
    private Long id;
    private Long productId;
    private Integer quantity;
}