package com.e_shop.controllers;

import com.e_shop.domain.Product;
import com.e_shop.services.OrderService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class RestAppControllerTest {

    OrderService orderServiceMock = mock(OrderService.class);
    RestAppController restAppController = new RestAppController(orderServiceMock);

    @Test
    public void getMyData() {
        Product productMock = mock(Product.class);
        List<Product> list = new ArrayList<>();
        list.add(productMock);
        when(productMock.getImagePath()).thenReturn("path/path2/path3");
        when(orderServiceMock.getBestsellers()).thenReturn(list);
        assertEquals(restAppController.getMyData(), list);
    }
}