package com.eshop.dao;

import com.eshop.domain.Client;
import com.eshop.domain.ClientAddress;
import com.eshop.domain.Order;
import com.eshop.domain.ShopAddress;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface OrderDao {

    Order getOrderById(Integer id);

    List<Order> getAllOrders();

    void saveOrders(Order order);

    int updateOrder(int orderId, String payStatus, String ordStatus);

    List<Order> getOrdersPerPeriod(LocalDate start, LocalDate finish);

    List<Order> getOrdersPerPeriodForClient(Client client, LocalDate start, LocalDate finish);

    void saveShop(ShopAddress address);

    Set<ShopAddress> getAllShops();

    ShopAddress getShopById(Integer id);

    ClientAddress getClientAddressById(Integer id);
}


