package com.eshop.domain;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Class to store order data during checkout process.
 */
@Data
public class Basket {

    private Map<Product, Integer> productsInBasket;

    /**
     * Calculates the total cost of all products in basket
     * @return total price of all products in basket
     */
    public double getTotalPrice() {
        double totalPrice = 0;
        Set<Product> products = productsInBasket.keySet();
        for (Product product: products) {
            totalPrice += product.getProductPrice();
        }
        return totalPrice;
    }

    public Basket() {
        this.productsInBasket = new HashMap<>();
    }
}
