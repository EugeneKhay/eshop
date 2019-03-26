package com.e_shop.services.impl;

import com.e_shop.dao.OrderDao;
import com.e_shop.domain.Basket;
import com.e_shop.domain.Client;
import com.e_shop.domain.Order;
import com.e_shop.domain.Product;
import com.e_shop.services.OrderService;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao dao;

    @Autowired
    private ProductService productService;

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
            totalSumOfAllOrders += order.getSumOfOrder();
        }
        return totalSumOfAllOrders;
    }

    //Need to check
    @Override
    public List<Product> getBestsellerPerPeriod(LocalDate start, LocalDate finish) {

        List<Product> allProducts = productService.getAllProducts();
        Map<Product, Long> productsOfPeriod = new HashMap<>();
        List<Order> orderList = getOrdersPerPeriod(start, finish);

        for (Product product: allProducts) {
            long x = 0;
            for (Order order: orderList) {
                long count = order.getProductsInOrder()
                                  .stream()
                                  .filter(p -> p.equals(product))
                                  .count();
                x += count;
            }
            productsOfPeriod.put(product, x);
                        System.out.println(productsOfPeriod);
        }
        List<Long> freqOfProducts = productsOfPeriod.values()
                                  .stream().sorted(Comparator.reverseOrder())
                                  .collect(Collectors.toList());
        Set<Product> bestTenProducts = new LinkedHashSet<>();
        freqOfProducts.forEach(p -> {
            for (Map.Entry<Product, Long> entry : productsOfPeriod.entrySet()) {
                if (p == entry.getValue())
                    bestTenProducts.add(entry.getKey());
            }
        });

        return bestTenProducts.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public double sumOfOrder(Basket basket) {
        double sum = 0;
        for (Map.Entry<Product, Integer> entry : basket.getProductsInBasket().entrySet()) {
            sum = sum + (entry.getKey().getProductPrice() * entry.getValue());
        }
        return sum;
    }








}
