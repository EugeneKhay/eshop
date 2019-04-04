package com.e_shop.controllers;

import com.e_shop.domain.Basket;
import com.e_shop.domain.Client;
import com.e_shop.domain.Order;
import com.e_shop.domain.Product;
import com.e_shop.enums.DeliveryMethod;
import com.e_shop.enums.OrderStatus;
import com.e_shop.enums.PaymentMethod;
import com.e_shop.enums.PaymentStatus;
import com.e_shop.services.ClientService;
import com.e_shop.services.OrderService;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

//    @GetMapping("/orders")
//    public String getOrders(Model model) {
//        model.addAttribute("orders", orderService.getAllOrders());
//        return "listoforders";
//    }

    @PostMapping("/confirm")
    public String confirmOrder(HttpSession session,
                               @RequestParam(name = "paymentMethod") String paymentMethod,
                               @RequestParam(name = "deliveryMethod") String deliveryMethod,
                               Model model) {
        Order order = new Order();
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Basket basket = (Basket) session.getAttribute("shop_basket");
        Set<Product> products = basket.getProductsInBasket().keySet();
        double sum = orderService.sumOfOrder(basket);
        order.setClient(client);
        order.setProductsInOrder(products);
        order.setDeliveryMethod(DeliveryMethod.valueOf(deliveryMethod));
        order.setPaymentMethod(PaymentMethod.valueOf(paymentMethod));
        order.setDateOfOrder(LocalDate.now());
        order.setSumOfOrder(sum);

        orderService.saveOrders(order);

        for (Map.Entry<Product, Integer> entry : basket.getProductsInBasket().entrySet()) {
            int amount = productService.decreaseProductAmountInStock(entry.getKey(), entry.getValue());
            productService.saveNewAmountOfProduct(entry.getKey(), amount);
        }
        Basket basket2 = (Basket) session.getAttribute("shop_basket");
        basket2.getProductsInBasket().clear();
        session.setAttribute("shop_basket", basket);
        session.setAttribute("totalPrice", 0);
        model.addAttribute("items", productService.getAllProducts());
        return "homepage2";
    }

    @PostMapping("/editorder")
    public String editOrderByAdmin(@RequestParam(name = "orderForEdit") int id,
                                   @RequestParam(name = "paymentStatus") String paymentStatus,
                                   @RequestParam(name = "orderStatus") String orderStatus,
                                   Model model) {
        Order orderForEditing = orderService.getOrderById(id);
        orderForEditing.setPaymentStatus(PaymentStatus.valueOf(paymentStatus));
        orderForEditing.setOrderStatus(OrderStatus.valueOf(orderStatus));
        orderService.updateOrder(id, paymentStatus, orderStatus);
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "adminpage";
    }
}
