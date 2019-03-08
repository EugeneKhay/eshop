package com.e_shop.dao;

import com.e_shop.domain.Order;
import com.e_shop.domain.Product;

import java.util.List;

public interface OrderDao {

    Order getOrderById(Integer id);

    List<Order> getAllOrders();

    void saveOrders(Order order);
}
