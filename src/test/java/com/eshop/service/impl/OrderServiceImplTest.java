//package com.eshop.service.impl;
//
//import com.eshop.dao.OrderDao;
//import com.eshop.dao.impl.OrderDaoImpl;
//import com.eshop.domain.Basket;
//import com.eshop.domain.Order;
//import com.eshop.domain.Product;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.mockito.Mockito.*;
//import static org.junit.Assert.*;
//
//public class OrderServiceImplTest {
//
//    private Basket basketMock = mock(Basket.class);
//    private Product productMock = mock(Product.class);
//    private OrderServiceImpl service = new OrderServiceImpl();
//    private LocalDate start = LocalDate.of(2019, 1, 1);
//    private LocalDate finish = LocalDate.of(2019, 2, 1);
//    private Order orderMock = mock(Order.class);
//    private List<Order> orders = new ArrayList<>();
//    private OrderDao daoMock = mock(OrderDaoImpl.class);
//
//    @Before
//    public void prepare() {
//        service.setDao(daoMock);
//        Map<Product, Integer> productsInBasketMock = new HashMap<>();
//        when(productMock.getProductPrice()).thenReturn(10000.0);
//        productsInBasketMock.put(productMock, 1);
//        basketMock.setProductsInBasket(productsInBasketMock);
//        List<Order> orders = new ArrayList<>();
//        when(daoMock.getOrdersPerPeriod(start, finish)).thenReturn(orders);
//    }
//
//    @Test
//    public void sumOfOrder() {
//        assertEquals(service.sumOfOrder(basketMock), 10000.0, 10000.0);
//    }
//
//    @Test
//    public void getTotalAmountOfOrdersPerPeriod() {
//        orders.add(orderMock);
//        when(service.getOrdersPerPeriod(start, finish)).thenReturn(orders);
//        assertEquals(service.getTotalAmountOfOrdersPerPeriod(start, finish), 1);
//    }
//
//    @Test
//    public void getTotalSumOfAllOrdersPerPeriod() {
//        orders.add(orderMock);
//        when(orderMock.getSumOfOrder()).thenReturn(10000.0);
//        assertEquals(service.getTotalAmountOfOrdersPerPeriod(start, finish), 10000.0, 10000.0);
//    }
//
//
//
//
//}