package com.e_shop.services.impl;

import com.e_shop.domain.Basket;
import com.e_shop.domain.Product;
import com.e_shop.enums.ProductCategory;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BasketService {

    @Autowired
    private ProductService productService;

    public Map<Product, Integer> getProductsInBasket(HttpServletRequest request) {
        Basket basket = (Basket) request.getSession().getAttribute("shop_basket");
        Map<Product, Integer> products = basket.getProductsInBasket();
        return products;
    }

    public List<Product> prepareProductsForBasket(@RequestParam(name = "item") int id,
                                                  @RequestParam(name = "page", required = false) String page,
                                                  HttpSession session) {
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
        ProductCategory category = product.getCategory();
        List<Product> products;
        //if came from homepage
        if (page == null) {
            products = productService.getAllProducts();
        } else
        // if came from page of any category of products
        products = productService.getAllProductsByCategory(category);
        return products;
    }


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
