package com.ElectronicStore.service.discount;


import com.ElectronicStore.entity.BasketItem;
import com.ElectronicStore.entity.DiscountDeal;
import com.ElectronicStore.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiscountOnPriceServiceTest {

    private DiscountOnPriceService discountService;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountOnPriceService();
    }

    @Test
    public void testApplyDiscount_NoDiscountDeal() {
        Product product = new Product();
        product.setPrice(100.0);

        BasketItem item = new BasketItem();
        item.setProduct(product);
        item.setQuantity(3);

        double result = discountService.applyDiscount(item, null);
        assertEquals(300.0, result);
    }

    @Test
    public void testApplyDiscount_WithDiscountDeal() {
        Product product = new Product();
        product.setPrice(100.0);

        BasketItem item = new BasketItem();
        item.setProduct(product);
        item.setQuantity(3);

        DiscountDeal discountDeal = mock(DiscountDeal.class);
        when(discountDeal.getRequiredPrice()).thenReturn(200.0);
        when(discountDeal.getDiscountPercentage()).thenReturn(0.5);

        double result = discountService.applyDiscount(item, discountDeal);
        assertEquals(150.0, result);
    }
}