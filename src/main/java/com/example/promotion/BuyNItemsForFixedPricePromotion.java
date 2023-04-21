package com.example.promotion;

import com.example.promotion.entity.OrderItem;

public class BuyNItemsForFixedPricePromotion implements IPromotion {
    private final String productId;
    private final int minQuantity;
    private final double fixedPrice;

    public BuyNItemsForFixedPricePromotion(String productId, int minQuantity, double fixedPrice) {
        this.productId = productId;
        this.minQuantity = minQuantity;
        this.fixedPrice = fixedPrice;
    }

    /**
     * Todo provide the implementation
     * @param item
     * @return
     */
    @Override
    public boolean isApplicable(OrderItem item) {
        return true;
    }

    /**
     * Todo provide the implementation
     * @param item
     * @return
     */
    @Override
    public double apply(OrderItem item) {
        return 0.0;
    }
}
