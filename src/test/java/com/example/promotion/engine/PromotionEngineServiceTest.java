package com.example.promotion.engine;

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
        List<Product> products = new ArrayList<>();
        products.add(productA);

        double expectedTotal = productA.getPrice();
        double actualTotal = service.apply(products);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void buyProductABTest() {
        List<Product> products = new ArrayList<>();
        products.add(productA);
        products.add(productB);

        double expectedTotal = productA.getPrice() + productB.getPrice();
        double actualTotal = service.apply(products);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void buyProductBCTest() {
        List<Product> products = new ArrayList<>();
        products.add(productC);
        products.add(productB);

        double expectedTotal = productC.getPrice() + productB.getPrice();
        double actualTotal = service.apply(products);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void buyProductABCTest() {
        List<Product> products = new ArrayList<>();
        products.add(productA);
        products.add(productB);
        products.add(productC);

        double expectedTotal = productA.getPrice() + productB.getPrice() + productC.getPrice();
        double actualTotal = service.apply(products);

        assertEquals(expectedTotal, actualTotal);
    }


}
