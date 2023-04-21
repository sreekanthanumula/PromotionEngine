package com.example.promotion.engine;

import com.example.promotion.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionEngineService {

    /**
     * @param products
     * Basic implementation to calculate the total cost with no promotions
     * @return total cost after applying the promotions
     */
    public double apply(List<Product> products) {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}
