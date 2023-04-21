package com.example.promotion;

import com.example.promotion.engine.PromotionEngineApplication;
import com.example.promotion.entity.Order;
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
        Order order = new Order();
        order.addItem(new OrderItem(productB));

        assertFalse(promotion.isApplicable(order));
    }

    @Test
    public void setPromotionAwithCTest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        Order order = new Order();
        order.addItem(new OrderItem(productC));

        assertFalse(promotion.isApplicable(order));
    }

    @Test
    public void setPromotionAwithATest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        Order order = new Order();
        order.addItem(new OrderItem(productA));

        assertFalse(promotion.isApplicable(order));
    }

    @Test
    public void calculateTotalPromotionABwithATest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        Order order = new Order();
        order.addItem(new OrderItem(productA, 5));

        double expected = (order.getItems().get(0).getProduct().getPrice() * 5);
        promotion.apply(order);
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void calculateTotalPromotionABwithABTest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        Order order = new Order();
        order.addItem(new OrderItem(productA));
        order.addItem(new OrderItem(productB));

        double expected = 70.0;
        promotion.apply(order);
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void calculateTotalPromotionABwith3ABTest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        Order order = new Order();
        order.addItem(new OrderItem(productA, 3));
        order.addItem(new OrderItem(productB, 3));

        double expected = 210.0;
        promotion.apply(order);
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void calculateTotalPromotionABwith3A2BTest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        Order order = new Order();
        order.addItem(new OrderItem(productA, 3));
        order.addItem(new OrderItem(productB, 2));

        double expected = 190.0;
        promotion.apply(order);
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void calculateTotalPromotionABwithCTest() {
        promotion = new BuyTwoItemsForFixedPricePromotion("A", "B", 70.0);
        Order order = new Order();
        order.addItem(new OrderItem(productC, 3));

        double expected = (order.getItems().get(0).getProduct().getPrice() * 3);
        promotion.apply(order);
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }
}

