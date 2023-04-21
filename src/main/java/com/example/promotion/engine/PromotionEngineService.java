package com.example.promotion.engine;

import com.example.promotion.IPromotion;
import com.example.promotion.entity.Order;
import com.example.promotion.entity.OrderItem;
import com.example.promotion.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionEngineService {

    private IPromotion promotion;

    /**
     * @param order
     * Basic implementation to calculate the total cost with no promotions
     * @return total cost after applying the promotions
     */
    public double apply(Order order) {
        double total = 0.0;
        for (OrderItem item : order.getItems()) {
            if (promotion != null && promotion.isApplicable(item)) {
                total += promotion.apply(item);
            } else {
                total += (item.getQuantity() * item.getProduct().getPrice());
            }
        }
        return total;
    }

    public void setPromotion(IPromotion promotion) {
        this.promotion = promotion;
    }
}
