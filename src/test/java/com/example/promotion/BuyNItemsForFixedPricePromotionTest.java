package com.example.promotion;

import com.example.promotion.engine.PromotionEngineApplication;
import com.example.promotion.entity.Order;
import com.example.promotion.entity.OrderItem;
import com.example.promotion.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PromotionEngineApplication.class)
@RunWith(SpringRunner.class)
public class BuyNItemsForFixedPricePromotionTest {

    IPromotion promotion;

    final Product productA = new Product("A", 50.0);
    final Product productB = new Product("B", 30.0);
    final Product productC = new Product("C", 20.0);

    @Test
    public void setPromotionAwithBTest() {
        promotion = new BuyNItemsForFixedPricePromotion("A", 3, 120.0);
        Order order = new Order();
        OrderItem item = new OrderItem(productB);
        order.addItem(item);

        assertFalse(promotion.isApplicable(order));
    }

    @Test
    public void setPromotionAwithCTest() {
        promotion = new BuyNItemsForFixedPricePromotion("A", 3, 120.0);
        Order order = new Order();
        OrderItem item = new OrderItem(productC);
        order.addItem(item);

        assertFalse(promotion.isApplicable(order));
    }

    @Test
    public void setPromotionAwithATest() {
        promotion = new BuyNItemsForFixedPricePromotion("A", 3, 120.0);
        Order order = new Order();
        OrderItem item = new OrderItem(productA);
        order.addItem(item);

        assertTrue(promotion.isApplicable(order));
    }

    @Test
    public void calculateTotalPromotionAwithATest() {
        promotion = new BuyNItemsForFixedPricePromotion("A", 3, 120.0);
        Order order = new Order();
        OrderItem item = new OrderItem(productA, 5);
        order.addItem(item);

        double expected = (120 + item.getProduct().getPrice() * 2);
        promotion.apply(order);
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }

    @Test
    public void calculateTotalPromotionAwithBTest() {
        promotion = new BuyNItemsForFixedPricePromotion("A", 3, 120.0);
        Order order = new Order();
        OrderItem item = new OrderItem(productB, 3);
        order.addItem(item);

        double expected = (item.getProduct().getPrice() * 3);
        promotion.apply(order);
        double actual = order.getTotalPrice();
        assertEquals(expected, actual);
    }
}
