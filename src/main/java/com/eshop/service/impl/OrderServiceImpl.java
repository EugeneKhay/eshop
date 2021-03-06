package com.eshop.service.impl;

import com.eshop.dao.OrderDao;
import com.eshop.dao.ShopAddressDAO;
import com.eshop.domain.*;
import com.eshop.enums.DeliveryMethod;
import com.eshop.enums.OrderStatus;
import com.eshop.enums.PaymentMethod;
import com.eshop.enums.PaymentStatus;
import com.eshop.exception.NoAddressException;
import com.eshop.exception.NoProductInBasketException;
import com.eshop.jms.MessageSender;
import com.eshop.sender.SmsSender;
import com.eshop.service.OrderService;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Contains implementations of methods OrderService for working with orders.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final LocalDate START_DATE = LocalDate.of(2019, 1, 1);

    private static final LocalDate FINISH_DATE = LocalDate.now();

    private static String BASKET_ATTR = "shop_basket";

    @Autowired
    private OrderDao dao;

    @Autowired
    private ShopAddressDAO shopDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private JavaMailSender mailSender;

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

    /**
     * Counts amount of orders for the particular period of time.
     * @param start start date
     * @param finish finish date
     * @return amount of orders for the particular period of time
     */
    public long getTotalAmountOfOrdersPerPeriod(LocalDate start, LocalDate finish) {
        return getOrdersPerPeriod(start, finish).size();
    }

    /**
     * Counts total sum of all orders for the particular period of time.
     * @param start start date
     * @param finish finish date
     * @return total sum of all orders for the particular period of time
     */
    @Override
    public double getTotalSumOfAllOrdersPerPeriod(LocalDate start, LocalDate finish) {
        List<Order> listOfOrdersPerPeriod = getOrdersPerPeriod(start, finish);
        double totalSumOfAllOrders = 0;
        for (Order order: listOfOrdersPerPeriod) {
            totalSumOfAllOrders += order.getSumOfOrder();
        }
        return totalSumOfAllOrders;
    }

    /**
     * Create list of 10 best products for the particular period of time.
     * @param start start date
     * @param finish finish date
     * @return list of 10 best products for the particular period of time
     */
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
                if (entry.getValue().equals(p))
                    bestTenProducts.add(entry.getKey());
            }
        });
        return bestTenProducts.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBestsellers() {
        return getBestsellerPerPeriod(START_DATE, FINISH_DATE);
    }

    /**
     * Calculates sum of all products in a particular order.
     * @param basket current state of basket
     * @return sum of all products in order
     */
    @Override
    public double sumOfOrder(Basket basket) {
        double sum = 0;
        for (Map.Entry<Product, Integer> entry : basket.getProductsInBasket().entrySet()) {
            sum = sum + (entry.getKey().getProductPrice() * entry.getValue());
        }
        return sum;
    }

    /**
     * Creates new instance of order and saves it to DB.
     * @param session current HTTP session
     * @param paymentMethod payment method of order
     * @param deliveryMethod delivery method of order
     * @param deliveryAddress delivery address of order
     * @param collectAddress address for self collect
     * @return instance of order, saved to DB
     */
    @Override
    public Order makeNewOrder(HttpSession session, String paymentMethod, String deliveryMethod,
                              Integer deliveryAddress, Integer collectAddress) {
        if (deliveryMethod.equals("COURIER") && deliveryAddress == null) {
            throw new NoAddressException("No address for delivery!");
        }
        if (deliveryMethod.equals("SELF") && collectAddress == null) {
            throw new NoAddressException("No address for self pickup!");
        }
        Order order = new Order();
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Basket basket = (Basket) session.getAttribute(BASKET_ATTR);
        if (deliveryAddress != null) {
            ClientAddress address = getClientAddressById(deliveryAddress);
            order.setAddressForDelivery(address);
        }
        if (collectAddress != null) {
            ShopAddress address = getShopById(collectAddress);
            order.setAddressForSelfCollect(address);
        }
        double sum = sumOfOrder(basket);
        order.setClient(client);
        Set<ProductToOrder> productToOrderList = new HashSet<>();
        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
            ProductToOrder productToOrder = new ProductToOrder();
            productToOrder.setProduct(entry.getKey());
            productToOrder.setAmount(entry.getValue());
            productToOrder.setOrder(order);
            productToOrderList.add(productToOrder);
        }
        order.setOrderProducts(productToOrderList);
        order.setDeliveryMethod(DeliveryMethod.valueOf(deliveryMethod));
        order.setPaymentMethod(PaymentMethod.valueOf(paymentMethod));
        order.setDateOfOrder(LocalDate.now());
        order.setSumOfOrder(sum);
        if (!(productToOrderList.isEmpty())) {
            saveOrders(order);
        } else {
            throw new NoProductInBasketException();
        }
        for (Map.Entry<Product, Integer> entry : basket.getProductsInBasket().entrySet()) {
            int amount = productService.decreaseProductAmountInStock(entry.getKey(), entry.getValue());
            productService.saveNewAmountOfProduct(entry.getKey(), amount);
        }
        Basket basket2 = (Basket) session.getAttribute(BASKET_ATTR);
        basket2.getProductsInBasket().clear();
        session.setAttribute(BASKET_ATTR, basket);
        session.setAttribute("totalPrice", 0);
        return order;
    }

    @Override
    public ClientAddress getClientAddressById(Integer deliveryAddress) {
        return dao.getClientAddressById(deliveryAddress);
    }

    @Override
    public ShopAddress getShopById(Integer id) {
        return shopDao.getShopById(id);
    }

    /**
     * Edits shop's address.
     * @param id id of the shop
     * @param country new country
     * @param city new city
     * @param postcode new post code
     * @param street new street
     * @param houseNumber new house number
     * @param phone new phone number
     */
    @Override
    public void editShopById(int id, String country, String city, int postcode, String street, int houseNumber, String phone) {
        ShopAddress shopById = getShopById(id);
        shopById.setCountry(country);
        shopById.setCity(city);
        shopById.setPostCode(postcode);
        shopById.setStreet(street);
        shopById.setHouseNumber(houseNumber);
        shopById.setPhoneNumber(phone);
        saveShop(shopById);
    }

    /**
     * Method for processing order by manager.
     * @param id id of the order
     * @param paymentStatus payment status ton set
     * @param orderStatus order status to set
     */
    @Override
    public void editOrder(int id, String paymentStatus, String orderStatus) {
        Order orderForEditing = getOrderById(id);
        orderForEditing.setPaymentStatus(PaymentStatus.valueOf(paymentStatus));
        orderForEditing.setOrderStatus(OrderStatus.valueOf(orderStatus));
        updateOrder(id, paymentStatus, orderStatus);
    }

    /**
     * Sends email and SMS messages.
     * @param client client
     * @param order order of the client
     */
    @Override
    public void sendMessages(Client client, Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("4358514@gmail.com");
        message.setTo(client.getEmail());
        message.setSubject("New order");
        message.setText("Thank you, " + client.getFirstName() + "! Your order number: " + order.getId());
        mailSender.send(message);
        SmsSender.sendSMS(client.getPhone(), order.getId());
    }

    @Override
    public void saveShop(ShopAddress address) {
        shopDao.saveShop(address);
    }

    @Override
    public Set<ShopAddress> getAllShops() {
        return shopDao.getAllShops();
    }

    public void setDao(OrderDao dao) {
        this.dao = dao;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}