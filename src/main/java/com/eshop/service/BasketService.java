package com.eshop.service;

import com.eshop.domain.Basket;
import com.eshop.domain.Product;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface BasketService {

    Map<Product, Integer> getProductsInBasket(HttpServletRequest request);

    Integer prepareProductsForBasket( int id, HttpSession session);

    Double deleteFromBAsket(int id, HttpSession session);

    List<Number> editOrderMinus(int id, HttpSession session);

    List<Number> editOrderPlus(int id, HttpSession session);

    List<Number> setDataToSession(Basket basket, Product product, int amountAfter, HttpSession session, double totalPrice);

}
