package com.ElectronicStore.service.discount;

import com.ElectronicStore.entity.BasketItem;
import com.ElectronicStore.entity.DiscountDeal;
import com.ElectronicStore.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiscountOnQuantityServiceTest {

    private DiscountOnQuantityService discountService;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountOnQuantityService();
    }

    @Test
    public void testApplyDiscount_NoDiscountDeal() {
        Product product = new Product();
        product.setPrice(100.0);

        BasketItem item = new BasketItem();
        item.setProduct(product);
        item.setQuantity(3);

        double result = discountService.applyDiscount(item, null);
        assertEquals(100.0, result);
    }

    @Test
    public void testApplyDiscount_WithDiscountDeal() {
        DiscountDeal discountDeal = mock(DiscountDeal.class);
        when(discountDeal.getRequiredQuantity()).thenReturn(1);
        when(discountDeal.getDiscountPercentage()).thenReturn(0.5);

        Product product = new Product();
        product.setPrice(100.0);

        BasketItem item = new BasketItem();
        item.setProduct(product);
        item.setQuantity(3);


        double result = discountService.applyDiscount(item, discountDeal);
        assertEquals(250.0, result);
    }
}