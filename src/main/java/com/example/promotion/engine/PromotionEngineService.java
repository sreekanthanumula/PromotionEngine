package com.example.promotion.engine;

import com.example.promotion.IPromotion;
import com.example.promotion.entity.Order;
import com.example.promotion.entity.OrderItem;
import com.example.promotion.entity.Product;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PromotionEngineService {

    private Set<IPromotion> promotions;

    public PromotionEngineService() {
        promotions = new HashSet<>();
    }

    /**
     * @param order Basic implementation to calculate the total cost with no promotions
     * @return total cost after applying the promotions
     */
    public double apply(Order order) {
        for(IPromotion promotion : promotions) {
            promotion.apply(order);
        }
        return order.getTotalPrice();
    }


    public void removePromotion(IPromotion promotion) {
        promotions.remove(promotion);
    }

    public void addPromotion(IPromotion promotion) {
        promotions.add(promotion);
    }

    public void clear() {
        promotions.clear();
    }
}
