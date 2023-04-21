package com.example.promotion;

import com.example.promotion.engine.PromotionEngineApplication;
import com.example.promotion.entity.OrderItem;
import com.example.promotion.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = PromotionEngineApplication.class)
@RunWith(SpringRunner.class)
public class BuyTwoItemsForFixedPricePromotionTest {
    IPromotion promotion;

    final Product productA = new Product("A", 50.0);
    final Product productB = new Product("B", 30.0);
    final Product productC = new Product("C", 20.0);

    @Test
    public void setPromotionABwithBTest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        OrderItem item = new OrderItem(productB);

        assertFalse(promotion.isApplicable(item));
    }

    @Test
    public void setPromotionAwithCTest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        OrderItem item = new OrderItem(productC);

        assertFalse(promotion.isApplicable(item));
    }

    @Test
    public void setPromotionAwithATest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        OrderItem item = new OrderItem(productA);

        assertTrue(promotion.isApplicable(item));
    }

    @Test
    public void calculateTotalPromotionABwithATest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        OrderItem item = new OrderItem(productA, 5);

        double expected = (120 + item.getProduct().getPrice() * 2);
        promotion.apply(item);
        double actual = item.getPrice() - item.getDiscount();
        assertEquals(expected, actual);
    }

    @Test
    public void calculateTotalPromotionABwithCTest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        OrderItem item = new OrderItem(productB, 3);

        double expected = (item.getProduct().getPrice() * 3);
        promotion.apply(item);
        double actual = item.getPrice() - item.getDiscount();
        assertEquals(expected, actual);
    }
}

