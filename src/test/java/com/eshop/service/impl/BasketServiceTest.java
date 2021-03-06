package com.eshop.service.impl;

import com.eshop.domain.Basket;
import com.eshop.domain.Product;
import com.eshop.service.BasketService;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BasketServiceTest {

    BasketService service = new BasketServiceImpl();
    Basket mockBasket = mock(Basket.class);
    Product mockProduct = mock(Product.class);
    HttpServletRequest mockRequest = mock(HttpServletRequest.class);
    HttpSession session = mock(HttpSession.class);
    Map<Product, Integer> mockMap;


    @Before
    public void prepareMap() {
        mockMap = new HashMap<>();
        mockMap.put(mockProduct, 2);
    }


    @Test
    public void getProductsInBasket() {
        when(mockRequest.getSession()).thenReturn(session);
        when(session.getAttribute("shop_basket")).thenReturn(mockBasket);
        when(mockBasket.getProductsInBasket()).thenReturn(mockMap);
        assertEquals(service.getProductsInBasket(mockRequest), mockMap);
    }


    @Test
    public void prepareProductsForBasket() {

    }

    @Test
    public void deleteFromBAsket() {
    }

    @Test
    public void editOrderMinus() {
    }

    @Test
    public void editOrderPlus() {
    }

    @Test
    public void setDataToSession() {
    }
}