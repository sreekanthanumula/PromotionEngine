package com.example.promotion;

import com.example.promotion.entity.OrderItem;
import org.springframework.stereotype.Component;

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
     * API to validate the promotion is applicable to order item
     * @param item
     * @return
     */
    @Override
    public boolean isApplicable(OrderItem item) {
        return productId.equals(item.getProduct().getId());
    }

    /**
     * API to apply the promotion to order item
     * @param item
     */
    @Override
    public void apply(OrderItem item) {
        if (isApplicable(item)) {
            int quotient = item.getQuantity() / minQuantity;
            int remainder = item.getQuantity() % minQuantity;
            double discount = item.getPrice() -
                    ((quotient * fixedPrice) + (remainder * item.getProduct().getPrice()));
            item.setDiscount(discount);
        }
    }
}
