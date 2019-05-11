package com.eshop.controller;

import com.eshop.domain.Client;
import com.eshop.domain.Order;
import com.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;

    private Logger logger = Logger.getLogger("logger");

    @PostMapping("/confirm")
    public String confirmOrder(HttpSession session,
                               @RequestParam(name = "paymentMethod") String paymentMethod,
                               @RequestParam(name = "deliveryMethod") String deliveryMethod,
                               @RequestParam(name = "deliveryAddress", required = false) String deliveryAddress,
                               @RequestParam(name = "collectAddress", required = false) String collectAddress,
                               Model model) {
        //TODO remove to service
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Order> orders = client.getOrders();
        //orders.add(orderService.makeNewOrder(session, paymentMethod, deliveryMethod, deliveryAddress));
        orders.add(orderService.makeNewOrder(session, paymentMethod, deliveryMethod, deliveryAddress, collectAddress));
        model.addAttribute("client", client);
        orderService.sendMessages();
        logger.info("The order created and saved to DB");
        return "personal";
    }

    @PostMapping("/editorder")
    public String editOrderByAdmin(@RequestParam(name = "orderForEdit") int id,
                                   @RequestParam(name = "paymentStatus") String paymentStatus,
                                   @RequestParam(name = "orderStatus") String orderStatus,
                                   Model model) {
        orderService.editOrder(id, paymentStatus, orderStatus);
        model.addAttribute("orders", orderService.getAllOrders());
        logger.info("The order's parameteres changed by admin");
        return "adminpage";
    }
}

























