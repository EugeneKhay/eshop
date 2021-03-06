package com.eshop.service;

import com.eshop.domain.*;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface OrderService {

    Order getOrderById(Integer id);

    List<Order> getAllOrders();

    void saveOrders(Order order);

    int updateOrder(int orderId, String payStatus, String ordStatus);

    List<Order> getOrdersPerPeriod(LocalDate start, LocalDate finish);

    double getTotalSumOfAllOrdersPerPeriod(LocalDate start, LocalDate finish);

    long getTotalAmountOfOrdersPerPeriod(LocalDate start, LocalDate finish);

    List<Product> getBestsellerPerPeriod(LocalDate start, LocalDate finish);

    List<Product> getBestsellers();

    double sumOfOrder(Basket basket);

    List<Order> getOrdersPerPeriodForClient(Client client, LocalDate start, LocalDate finish);

    Order makeNewOrder(HttpSession session, String paymentMethod, String deliveryMethod, Integer deliveryAddress, Integer collectAddress);

    void editOrder(int id, String paymentStatus, String orderStatus);

    void sendMessages(Client client, Order order);

    void saveShop(ShopAddress address);

    Set<ShopAddress> getAllShops();

    ClientAddress getClientAddressById(Integer id);

    ShopAddress getShopById(Integer id);

    void editShopById(int id, String country, String city, int postcode, String street, int houseNumber, String phone);


}