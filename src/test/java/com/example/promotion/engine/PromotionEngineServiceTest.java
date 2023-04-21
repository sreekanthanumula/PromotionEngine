package com.example.promotion.engine;

import com.example.promotion.entity.Order;
import com.example.promotion.entity.OrderItem;
import com.example.promotion.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = PromotionEngineApplication.class)
@RunWith(SpringRunner.class)
public class PromotionEngineServiceTest {

    @Autowired
    PromotionEngineService service;

    final Product productA = new Product("A", 50.0);
    final Product productB = new Product("B", 30.0);
    final Product productC = new Product("C", 20.0);

    // Tests with no promotions
    @Test
    public void buyProductATest() {
        Order order = new Order();
        order.addItem(new OrderItem(productA));

        double expectedTotal = productA.getPrice();
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void buyProductABTest() {
        Order order = new Order();
        order.addItem(new OrderItem(productA));
        order.addItem(new OrderItem(productB));

        double expectedTotal = productA.getPrice() + productB.getPrice();
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void buyProductBCTest() {
        Order order = new Order();
        order.addItem(new OrderItem(productB));
        order.addItem(new OrderItem(productC));

        double expectedTotal = productC.getPrice() + productB.getPrice();
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void buyProductABCTest() {
        Order order = new Order();
        order.addItem(new OrderItem(productA));
        order.addItem(new OrderItem(productB));
        order.addItem(new OrderItem(productC));


        double expectedTotal = productA.getPrice() + productB.getPrice() + productC.getPrice();
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

    // Tests with quantity and no promotions
    @Test
    public void buyProduct3ATest() {
        Order order = new Order();
        order.addItem(new OrderItem(productA, 3));

        double expectedTotal = productA.getPrice() * 3;
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void buyProduct5ABTest() {
        Order order = new Order();
        order.addItem(new OrderItem(productA, 5));
        order.addItem(new OrderItem(productB, 5));

        double expectedTotal = (productA.getPrice() * 5) + (productB.getPrice());
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void buyProduct3B4CTest() {
        Order order = new Order();
        order.addItem(new OrderItem(productB));
        order.addItem(new OrderItem(productC));

        double expectedTotal = (productC.getPrice() * 4) + (3 * productB.getPrice());
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void buyProduct2A3B4CTest() {
        Order order = new Order();
        order.addItem(new OrderItem(productA, 2));
        order.addItem(new OrderItem(productB, 3));
        order.addItem(new OrderItem(productC, 4));


        double expectedTotal = (productA.getPrice() * 2) + (productB.getPrice() * 3) + (productC.getPrice() * 4);
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

}
