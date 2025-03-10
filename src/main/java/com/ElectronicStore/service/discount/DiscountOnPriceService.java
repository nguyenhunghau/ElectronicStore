package com.ElectronicStore.service.discount;

import com.ElectronicStore.entity.BasketItem;
import com.ElectronicStore.entity.DiscountDeal;

public class DiscountOnPriceService implements IDiscountService{

    @Override
    public double applyDiscount(BasketItem item, DiscountDeal discountDeal) {
        double total = item.getProduct().getPrice() * item.getQuantity();
        if(discountDeal == null){
            return total;
        }
        if(total > discountDeal.getRequiredPrice()){
            return total * discountDeal.getDiscountPercentage();
        }
        return total;
    }
}
