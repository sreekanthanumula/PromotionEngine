package com.example.promotion.engine;

import com.example.promotion.BuyNItemsForFixedPricePromotion;
import com.example.promotion.BuyTwoItemsForFixedPricePromotion;
import com.example.promotion.BuyTwoItemsForFixedPricePromotionTest;
import com.example.promotion.IPromotion;
import com.example.promotion.entity.Order;
import com.example.promotion.entity.OrderItem;
import com.example.promotion.entity.Product;
import org.junit.Before;
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

    final Product productD = new Product("D", 15);

    @Before
    public void clearPromotions() {
        service.clear();
    }

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

        double expectedTotal = (productA.getPrice() * 5) + (productB.getPrice() * 5);
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void buyProduct3B4CTest() {
        Order order = new Order();
        order.addItem(new OrderItem(productB, 3));
        order.addItem(new OrderItem(productC, 4));

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

    @Test
    public void buy5AwithbuyNItemsPromotionTest() {
        Order order = new Order();
        order.addItem(new OrderItem(productA, 5));
        order.addItem(new OrderItem(productB, 3));
        order.addItem(new OrderItem(productC, 4));

        IPromotion buy3AFor120 = new BuyNItemsForFixedPricePromotion("A", 3, 120);
        service.addPromotion(buy3AFor120);


        double expectedTotal = 120 +  (productA.getPrice() * 2) + (productB.getPrice() * 3) + (productC.getPrice() * 4);
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);

    }

    @Test
    public void scenarioATest() {
        addAllPromotions();

        Order order = new Order();
        order.addItem(new OrderItem(productA ));
        order.addItem(new OrderItem(productB ));
        order.addItem(new OrderItem(productC ));

        double expectedTotal = 100;
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void scenarioBTest() {
        addAllPromotions();

        Order order = new Order();
        order.addItem(new OrderItem(productA,5 ));
        order.addItem(new OrderItem(productB, 5 ));
        order.addItem(new OrderItem(productC ));

        double expectedTotal = 370;
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    public void scenarioCTest() {
        addAllPromotions();

        Order order = new Order();
        order.addItem(new OrderItem(productA, 3 ));
        order.addItem(new OrderItem(productB, 5 ));
        order.addItem(new OrderItem(productC ));
        order.addItem(new OrderItem(productD));

        double expectedTotal = 280;
        double actualTotal = service.apply(order);

        assertEquals(expectedTotal, actualTotal);
    }

    private void addAllPromotions() {
        IPromotion buy3Afor130 = new BuyNItemsForFixedPricePromotion("A", 3, 130);
        IPromotion buy2Bfor45 = new BuyNItemsForFixedPricePromotion("B", 2, 45);
        IPromotion buyCDfor30 =  new BuyTwoItemsForFixedPricePromotion("C", "D", 30);

        service.addPromotion(buy3Afor130);
        service.addPromotion(buy2Bfor45);
        service.addPromotion(buyCDfor30);
    }

}
