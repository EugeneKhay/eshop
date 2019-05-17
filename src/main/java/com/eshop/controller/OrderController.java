package com.eshop.controller;

import com.eshop.domain.Client;
import com.eshop.domain.Order;
import com.eshop.service.AdminService;
import com.eshop.service.ClientService;
import com.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Controller class for order actions handling.
 */
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

    /**
     * Handle the request to "/confirm" URL to create new order. Sends SMS and email messages.
     * @param session current HTTP session
     * @param paymentMethod payment method chosen by client
     * @param deliveryMethod delivery method chosen by client
     * @param deliveryAddress delivery address chosen by client
     * @param collectAddress address for self collect chosen by client
     * @param model model for view
     * @return name of the corresponding view
     */
    @PostMapping("/confirm")
    public String confirmOrder(HttpSession session,
                               @RequestParam(name = "paymentMethod") String paymentMethod,
                               @RequestParam(name = "deliveryMethod") String deliveryMethod,
                               @RequestParam(name = "deliveryAddress", required = false) Integer deliveryAddress,
                               @RequestParam(name = "collectAddress", required = false) Integer collectAddress,
                               Model model) {
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Order> orders = client.getOrders();
        Order order = orderService.makeNewOrder(session, paymentMethod, deliveryMethod, deliveryAddress, collectAddress);
        orders.add(order);
        Integer id = client.getId();
        Client clientForView = clientService.getClientById(id);
        model.addAttribute("client", clientForView);
        orderService.sendMessages(clientForView, order);
        logger.info("The order created and saved to DB");
        return "personal";
    }

    /**
     * Handle the request to "/editorder" URL to edit order by manager.
     * @param id id of the order to edit
     * @param paymentStatus payment status of the order
     * @param orderStatus status of the order
     * @param model model for view
     * @return name of the corresponding view
     */
    @PostMapping("/editorder")
    public String editOrderByAdmin(@RequestParam(name = "orderForEdit") Integer id,
                                   @RequestParam(name = "paymentStatus") String paymentStatus,
                                   @RequestParam(name = "orderStatus") String orderStatus,
                                   Model model) {
        if (id == null) {
            adminService.setStatsDefaultDate(model);
            return "adminpage";
        }
        orderService.editOrder(id, paymentStatus, orderStatus);
        logger.info("The order's parameteres changed by admin");
        adminService.setStatsDefaultDate(model);
        return "adminpage";
    }
}