package com.e_shop.services;

import com.e_shop.domain.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(Integer id);

    List<Order> getAllOrders();

    void saveOrders(Order order);

//    int updateOrder(Order order, String payStatus, String ordStatus);

    int updateOrder(int orderId, String payStatus, String ordStatus);


}
