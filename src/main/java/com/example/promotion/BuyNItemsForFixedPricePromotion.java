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
        return productId.equals(item.getProduct().getId());
    }

    /**
     * Todo provide the implementation
     * @param item
     * @return
     */
    @Override
    public double apply(OrderItem item) {
        double total = 0.0;
        if (isApplicable(item)) {
            int quotient = item.getQuantity() / minQuantity;
            int remainder = item.getQuantity() % minQuantity;
            total = (quotient * fixedPrice) + (remainder * item.getProduct().getPrice());
        }
        return total;
    }
}
