package com.example.promotion.engine;

import com.example.promotion.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionEngineService {

    /**
     * Todo provide implementation to apply the the promotions
     * @param products
     * @return total cost after applying the promotions
     */
    public double apply(List<Product> products) {
        return 0.0;
    }
}
