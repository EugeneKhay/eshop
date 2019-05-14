package com.eshop.service.impl;

import com.eshop.domain.Basket;
import com.eshop.domain.Product;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BasketService {

    @Autowired
    private ProductService productService;

    /**
     * Get products from current state of basket from session
     * @param request http request from client side
     * @return map contains products and its amount
     */
    public Map<Product, Integer> getProductsInBasket(HttpServletRequest request) {
        Basket basket = (Basket) request.getSession().getAttribute("shop_basket");
        Map<Product, Integer> products = basket.getProductsInBasket();
        return products;
    }

    /**
     * Add one item of product to the basket.
     * @param id id of product to be added to the basket
     * @param session the current HTTP session
     * @return total amount of all product items
     */
    public Integer prepareProductsForBasket( int id, HttpSession session) {
        Product product = productService.getProductById(id);
        Basket basket = (Basket) session.getAttribute("shop_basket");
        if (basket.getProductsInBasket().containsKey(product)) {
            int amountInBasketBeforeAdd = basket.getProductsInBasket().get(product);
            basket.getProductsInBasket().put(product, amountInBasketBeforeAdd + 1);
        } else {
            basket.getProductsInBasket().put(product, 1);
        }
        session.setAttribute("shop_basket", basket);
        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
            totalPrice += (entry.getKey().getProductPrice() * entry.getValue());
        }
        session.setAttribute("totalPrice", totalPrice);
        Integer amountOfItemsInBasket = ((Basket) session.getAttribute("shop_basket")).getProductsInBasket()
                .values()
                .stream()
                .reduce((s1, s2) -> s1 + s2)
                .orElse(0);
        return amountOfItemsInBasket;
    }

    /**
     * Removes all products of the same type defined by id from the basket and set
     * the updated state of basket to the session.
     * @param id id of product to be removed from the basket
     * @param session the current HTTP session
     * @return the total price of all products in the basket
     */
    public Double deleteFromBAsket(int id, HttpSession session) {
        Product product = productService.getProductById(id);
        Basket basket = (Basket) session.getAttribute("shop_basket");
        Double totalPrice = (Double) session.getAttribute("totalPrice");
        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
            if (entry.getKey().equals(product))
                totalPrice = totalPrice - (entry.getKey().getProductPrice() * entry.getValue());
        }
        basket.getProductsInBasket().remove(product);
        session.setAttribute("shop_basket", basket);
        session.setAttribute("totalPrice", totalPrice);
        return totalPrice;
    }

    /**
     * Decrease the number of product items in the basket.
     * @param id id of product one item of which to be removed from the basket
     * @param session the current HTTP session
     * @return list of numbers that contains new total price and new amount
     * of particular product in the basket
     */
    public List<Number> editOrderMinus(int id, HttpSession session) {
        Product product = productService.getProductById(id);
        Basket basket = (Basket) session.getAttribute("shop_basket");
        double totalPrice = (double) session.getAttribute("totalPrice");
        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
            if (entry.getKey().equals(product))
                totalPrice = totalPrice - entry.getKey().getProductPrice();
            if (totalPrice < 0) totalPrice = 0;
        }
        int amountBefore = basket.getProductsInBasket().get(product);
        int amountAfter = --amountBefore;
        if (amountAfter < 0) amountAfter = 0;
        List<Number> result = setDataToSession(basket, product, amountAfter, session, totalPrice);
        return result;
    }

    /**
     * Increase the number of product items in the basket.
     * @param id id of product amount of which to be increased to the basket
     * @param session the current HTTP session
     * @return list of numbers that contains new total price and new amount
     * of particular product in the basket
     */
    public List<Number> editOrderPlus(int id, HttpSession session) {
        Product product = productService.getProductById(id);
        Basket basket = (Basket) session.getAttribute("shop_basket");
        double totalPrice = (double) session.getAttribute("totalPrice");
        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
            if (entry.getKey().equals(product))
                totalPrice = totalPrice + entry.getKey().getProductPrice();
        }
        int amountBefore = basket.getProductsInBasket().get(product);
        int amountAfter = ++amountBefore;
        List<Number> result = setDataToSession(basket, product, amountAfter, session, totalPrice);
        return result;
    }

    /**
     * Set total price and amount of products in basket after adding product
     * @param basket current state of basket from session
     * @param product product to put to the basket
     * @param amountAfter the amount of product in the basket after changes
     * @param session the current HTTP session
     * @param totalPrice total price of all products in the basket
     * @return list of numbers that contains total price and amount
     * of particular product in the basket
     */
    public List<Number> setDataToSession(Basket basket, Product product, int amountAfter, HttpSession session, double totalPrice) {
        basket.getProductsInBasket().put(product, amountAfter);
        session.setAttribute("shop_basket", basket);
        session.setAttribute("totalPrice", totalPrice);
        List<Number> result = new ArrayList();
        result.add(totalPrice);
        result.add(amountAfter);
        return result;
    }
}
