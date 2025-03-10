package com.ElectronicStore.service.discount;

import com.ElectronicStore.entity.BasketItem;
import com.ElectronicStore.entity.DiscountDeal;

public interface IDiscountService {
    double applyDiscount(BasketItem item, DiscountDeal discountDeal);
}
