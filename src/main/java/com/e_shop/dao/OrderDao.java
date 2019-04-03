package com.e_shop.dao;

import com.e_shop.domain.Client;
import com.e_shop.domain.Order;
import com.e_shop.domain.Product;

import java.time.LocalDate;
import java.util.List;

public interface OrderDao {

    Order getOrderById(Integer id);

    List<Order> getAllOrders();

    void saveOrders(Order order);

//    int updateOrder(Order order, String payStatus, String ordStatus);

    int updateOrder(int orderId, String payStatus, String ordStatus);

    List<Order> getOrdersPerPeriod(LocalDate start, LocalDate finish);

    List<Order> getOrdersPerPeriodForClient(Client client, LocalDate start, LocalDate finish);

}


