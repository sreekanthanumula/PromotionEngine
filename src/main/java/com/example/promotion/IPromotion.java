package com.example.promotion;

import com.example.promotion.entity.Order;

public interface IPromotion {
    /**
     * API to verify the promotion applicable to specific order item
     * @param order
     * @return
     */
    boolean isApplicable(Order order);

    /**
     * API to apply the promotion to order item.
     * @param order
     * @return
     */
    void apply(Order order);
}
