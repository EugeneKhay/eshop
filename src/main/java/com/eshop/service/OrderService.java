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

    LocalDate getDate(String day, String month, String year);

    double getTotalSumOfAllOrdersPerPeriod(LocalDate start, LocalDate finish);

    long getTotalAmountOfOrdersPerPeriod(LocalDate start, LocalDate finish);

    List<Product> getBestsellerPerPeriod(LocalDate start, LocalDate finish);

    List<Product> getBestsellers();

    double sumOfOrder(Basket basket);

    List<Order> getOrdersPerPeriodForClient(Client client, LocalDate start, LocalDate finish);

    Order makeNewOrder(HttpSession session, String paymentMethod, String deliveryMethod);

    //Order makeNewOrder(HttpSession session, String paymentMethod, String deliveryMethod, String deliveryAddress);
    Order makeNewOrder(HttpSession session, String paymentMethod, String deliveryMethod, String deliveryAddress, String collectAffress);

    void editOrder(int id, String paymentStatus, String orderStatus);

    void sendMessages();

    void saveShop(ShopAddress address);

    Set<ShopAddress> getAllShops();


}