package com.e_shop.services.impl;

import com.e_shop.dao.OrderDao;
import com.e_shop.domain.Order;
import com.e_shop.domain.Product;
import com.e_shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao dao;

    @Override
    public Order getOrderById(Integer id) {
        return dao.getOrderById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return dao.getAllOrders();
    }

    @Override
    public void saveOrders(Order order) {
        dao.saveOrders(order);
    }

    @Override
    public int updateOrder(int orderId, String payStatus, String ordStatus) {
        return dao.updateOrder(orderId, payStatus, ordStatus);
    }

    @Override
    public List<Order> getOrdersPerPeriod(LocalDate start, LocalDate finish) {
        return dao.getOrdersPerPeriod(start, finish);
    }

    public LocalDate getDate(String day, String month, String year) {
        String[] array = {day, month, year};
        int[] dateInInt = Arrays.stream(array).mapToInt(Integer::valueOf).toArray();
        LocalDate date = LocalDate.of(dateInInt[2], dateInInt[1], dateInInt[0]);
        return date;
    }

    public long getTotalAmountOfOrdersPerPeriod(LocalDate start, LocalDate finish) {
        return getOrdersPerPeriod(start, finish).size();
    }

    @Override
    public double getTotalSumOfAllOrdersPerPeriod(LocalDate start, LocalDate finish) {
        List<Order> listOfOrdersPerPeriod = getOrdersPerPeriod(start, finish);
        double totalSumOfAllOrders = 0;
        for (Order order: listOfOrdersPerPeriod) {
            double sumInOrder = order.getProductsInOrder()
                    .stream()
                    .mapToDouble(p -> p.getProductPrice())
                    .sum();
            totalSumOfAllOrders += sumInOrder;
        }
        return totalSumOfAllOrders;
    }


//    @Override
//    public double getTotalSumOfAllOrdersPerPeriod(LocalDate start, LocalDate finish) {
//        List<Order> listOfOrdersPerPeriod = getOrdersPerPeriod(start, finish);
//        double totalSumOfAllOrders = 0;
//        for (Order order: listOfOrdersPerPeriod) {
//            double sumInOrder = 0;
//            for (Map.Entry<Product, Integer> entry: order.getProductsInOrder().entrySet()) {
//                sumInOrder = entry.getKey().getProductPrice() * entry.getValue();
//            }
//            totalSumOfAllOrders += sumInOrder;
//        }
//        return totalSumOfAllOrders;
//    }
//



    @Override
    public Product getBestsellerPerPeriod(LocalDate start, LocalDate finish) {
//
//        List<Order> orderList = getOrdersPerPeriod(start, finish);
//        Map<Product, Long> compareProductsMap = new HashMap<>();
//
//        for (Order order: orderList) {
//            Map<Product, Integer> productsInOrder = order.getProductsInOrder();
//            for (Map.Entry<Product, Integer> entry : productsInOrder.entrySet()) {
//
//            }
//
//        }
        return null;
    }


}
