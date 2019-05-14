package com.eshop.controller;

import com.eshop.domain.Client;
import com.eshop.domain.Order;
import com.eshop.service.ClientService;
import com.eshop.service.OrderService;
import com.eshop.service.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AdminService adminService;

    private Logger logger = Logger.getLogger("logger");

    @PostMapping("/confirm")
    public String confirmOrder(HttpSession session,
                               @RequestParam(name = "paymentMethod") String paymentMethod,
                               @RequestParam(name = "deliveryMethod") String deliveryMethod,
                               @RequestParam(name = "deliveryAddress", required = false) Integer deliveryAddress,
                               @RequestParam(name = "collectAddress", required = false) Integer collectAddress,
                               Model model) {
        //TODO remove to service
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Order> orders = client.getOrders();
        Order order = orderService.makeNewOrder(session, paymentMethod, deliveryMethod, deliveryAddress, collectAddress);
        orders.add(order);
        Integer id = client.getId();
        Client clientForView = clientService.getClientById(id);

        model.addAttribute("client", clientForView);

        //Turn on messages sending
        //orderService.sendMessages(clientForView, order);

        logger.info("The order created and saved to DB");
        return "personal";
    }

    @PostMapping("/editorder")
    public String editOrderByAdmin(@RequestParam(name = "orderForEdit") int id,
                                   @RequestParam(name = "paymentStatus") String paymentStatus,
                                   @RequestParam(name = "orderStatus") String orderStatus,
                                   Model model) {
        orderService.editOrder(id, paymentStatus, orderStatus);
        //model.addAttribute("orders", orderService.getAllOrders());
        logger.info("The order's parameteres changed by admin");
        //TODO make separate
//        LocalDate start = LocalDate.of(2019, 1, 1);
//        LocalDate finish = LocalDate.now();
//        adminService.setStats(model, start, finish);
        adminService.setStatsDefaultDate(model);
        return "adminpage";
    }
}

























