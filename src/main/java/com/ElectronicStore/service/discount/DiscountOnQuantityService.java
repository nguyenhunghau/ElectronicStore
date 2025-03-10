package com.ElectronicStore.service.discount;

import com.ElectronicStore.entity.BasketItem;
import com.ElectronicStore.entity.DiscountDeal;

/**
 * Discount 50% from 2nd item
 */
public class DiscountOnQuantityService implements IDiscountService{

    @Override
    public double applyDiscount(BasketItem item, DiscountDeal discountDeal) {
        double firstPrice = item.getProduct().getPrice();
        if(discountDeal == null){
            return firstPrice;
        }
        if(item.getQuantity() > discountDeal.getRequiredQuantity() + 1){
            return firstPrice + (item.getQuantity() - discountDeal.getRequiredPrice()) * firstPrice * discountDeal.getDiscountPercentage();
        }
        return firstPrice;
    }
}
