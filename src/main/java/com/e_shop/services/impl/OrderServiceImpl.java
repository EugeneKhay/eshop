package com.e_shop.services.impl;

import com.e_shop.dao.OrderDao;
import com.e_shop.domain.*;
import com.e_shop.enums.DeliveryMethod;
import com.e_shop.enums.OrderStatus;
import com.e_shop.enums.PaymentMethod;
import com.e_shop.enums.PaymentStatus;
import com.e_shop.services.OrderService;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    public static final LocalDate START_DATE = LocalDate.of(2019, 1, 1);

    public static final LocalDate FINISH_DATE = LocalDate.now();

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

    @Override
    public List<Order> getOrdersPerPeriodForClient(Client client, LocalDate start, LocalDate finish) {
        return dao.getOrdersPerPeriodForClient(client, start, finish);
    }

    //Delete
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

    @Override
    public List<Product> getBestsellerPerPeriod(LocalDate start, LocalDate finish) {

        List<Product> allProducts = productService.getAllProducts();
        Map<Product, Long> productsOfPeriod = new HashMap<>();
        List<Order> orderList = getOrdersPerPeriod(start, finish);

        for (Product product: allProducts) {
            long x = 0;
            for (Order order: orderList) {
                long count = order.getOrderProducts()
                        .stream()
                        .filter(p -> p.getProduct().equals(product))
                        .count();
                x += count;
            }
            productsOfPeriod.put(product, x);
        }
        List<Long> freqOfProducts = productsOfPeriod.values()
                .stream().sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        Set<Product> bestTenProducts = new LinkedHashSet<>();
        freqOfProducts.forEach(p -> {
            for (Map.Entry<Product, Long> entry : productsOfPeriod.entrySet()) {
                if (entry.getValue() == p)
                    bestTenProducts.add(entry.getKey());
            }
        });
        return bestTenProducts.stream().limit(12).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBestsellers() {
        return getBestsellerPerPeriod(START_DATE, FINISH_DATE);
    }

    @Override
    public double sumOfOrder(Basket basket) {
        double sum = 0;
        for (Map.Entry<Product, Integer> entry : basket.getProductsInBasket().entrySet()) {
            sum = sum + (entry.getKey().getProductPrice() * entry.getValue());
        }
        return sum;
    }

    @Override
    public Order makeNewOrder(HttpSession session, String paymentMethod, String deliveryMethod) {
        Order order = new Order();
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Basket basket = (Basket) session.getAttribute("shop_basket");
//        Set<Product> products = basket.getProductsInBasket().keySet();
        double sum = sumOfOrder(basket);
        order.setClient(client);

//        //EXP
        List<ProductToOrder> productToOrderList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
            ProductToOrder productToOrder = new ProductToOrder();
            productToOrder.setProduct(entry.getKey());
            productToOrder.setAmount(entry.getValue());
            productToOrder.setOrder(order);
            productToOrderList.add(productToOrder);
        }

//        order.setProductsInOrder(products);
        order.setOrderProducts(productToOrderList);
        order.setDeliveryMethod(DeliveryMethod.valueOf(deliveryMethod));
        order.setPaymentMethod(PaymentMethod.valueOf(paymentMethod));
        order.setDateOfOrder(LocalDate.now());
        order.setSumOfOrder(sum);
        saveOrders(order);
        for (Map.Entry<Product, Integer> entry : basket.getProductsInBasket().entrySet()) {
            int amount = productService.decreaseProductAmountInStock(entry.getKey(), entry.getValue());
            productService.saveNewAmountOfProduct(entry.getKey(), amount);
        }
        Basket basket2 = (Basket) session.getAttribute("shop_basket");
        basket2.getProductsInBasket().clear();
        session.setAttribute("shop_basket", basket);
        session.setAttribute("totalPrice", 0);
        return order;
    }

    @Override
    public void editOrder(int id, String paymentStatus, String orderStatus) {
        Order orderForEditing = getOrderById(id);
        orderForEditing.setPaymentStatus(PaymentStatus.valueOf(paymentStatus));
        orderForEditing.setOrderStatus(OrderStatus.valueOf(orderStatus));
        updateOrder(id, paymentStatus, orderStatus);
    }

    public void setDao(OrderDao dao) {
        this.dao = dao;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}








//    @Override
//    public List<Product> getBestsellerPerPeriod(LocalDate start, LocalDate finish) {
//
//        List<Product> allProducts = productService.getAllProducts();
//        Map<Product, Long> productsOfPeriod = new HashMap<>();
//        List<Order> orderList = getOrdersPerPeriod(start, finish);
//
//        for (Product product: allProducts) {
//            long x = 0;
//            for (Order order: orderList) {
//                long count = order.getProductsInOrder()
//                                  .stream()
//                                  .filter(p -> p.equals(product))
//                                  .count();
//                x += count;
//            }
//            productsOfPeriod.put(product, x);
////                        System.out.println(productsOfPeriod);
//        }
//        List<Long> freqOfProducts = productsOfPeriod.values()
//                                  .stream().sorted(Comparator.reverseOrder())
//                                  .collect(Collectors.toList());
//        Set<Product> bestTenProducts = new LinkedHashSet<>();
//        freqOfProducts.forEach(p -> {
//            for (Map.Entry<Product, Long> entry : productsOfPeriod.entrySet()) {
//                if (p == entry.getValue())
//                    bestTenProducts.add(entry.getKey());
//            }
//        });
//        return bestTenProducts.stream().limit(12).collect(Collectors.toList());
//    }
