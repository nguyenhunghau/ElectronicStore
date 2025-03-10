package com.ElectronicStore.service.discount;


import com.ElectronicStore.dto.DiscountType;

public class DiscountDealFactory {

    public static IDiscountService getDiscountService(DiscountType discountType) {
        switch (discountType) {
            case PRICE -> {
                return new DiscountOnPriceService();
            }
            case QUANTITY -> {
                return new DiscountOnQuantityService();
            }
            default -> throw new IllegalArgumentException("Invalid discount type");
        }
    }
}