package com.example.promotion;

import com.example.promotion.entity.Order;
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
     * @param order
     * @return
     */
    @Override
    public boolean isApplicable(Order order) {
        for (OrderItem item : order.getItems()) {
            if (item.getProduct().getId().equals(productId) && !item.isPromotionApplied())
                return true;
        }
        return false;
    }

    /**
     * API to apply the promotion to order item
     * @param order
     */
    @Override
    public void apply(Order order) {
        for (OrderItem item : order.getItems()) {
            if (item.getProduct().getId().equals(productId)) {
                int quotient = item.getQuantity() / minQuantity;
                int remainder = item.getQuantity() % minQuantity;
                double discount = item.getPrice() -
                        ((quotient * fixedPrice) + (remainder * item.getProduct().getPrice()));
                if (!item.isPromotionApplied()) {
                    item.setDiscount(discount);
                    item.setPromotionApplied(true);
                }
            }
        }
    }
}
