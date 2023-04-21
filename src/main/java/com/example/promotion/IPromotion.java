package com.example.promotion;

import com.example.promotion.entity.OrderItem;

public interface IPromotion {
    /**
     * API to verify the promotion applicable to specific order item
     * @param item
     * @return
     */
    boolean isApplicable(OrderItem item);

    /**
     * API to apply the promotion to order item.
     * @param item
     * @return
     */
    double apply(OrderItem item);
}
