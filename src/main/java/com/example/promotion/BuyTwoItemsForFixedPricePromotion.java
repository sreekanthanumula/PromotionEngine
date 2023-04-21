package com.example.promotion;

import com.example.promotion.entity.OrderItem;

public class BuyTwoItemsForFixedPricePromotion implements IPromotion {

    private final String product1;
    private final String product2;
    private final double fixedPrice;

    public BuyTwoItemsForFixedPricePromotion(String product1, String product2, double fixedPrice) {
        this.product1 = product1;
        this.product2 = product2;
        this.fixedPrice = fixedPrice;
    }

    /**
     * API to verify the promotion applicable to specific order item
     * Todo provide the implementation
     * @param item
     * @return
     */
    @Override
    public boolean isApplicable(OrderItem item) {
        return false;
    }

    /**
     * API to apply the promotion to order item.
     * Todo provide the implementation
     * @param item
     * @return
     */
    @Override
    public void apply(OrderItem item) {

    }
}
