package com.eshop.domain;

import lombok.Data;

import java.util.*;

@Data
public class Basket {

    private Map<Product, Integer> productsInBasket;

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
