package com.e_shop.controllers;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String order() {
        return "addorder";
    }

    @GetMapping("/orders")
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "listoforders";
    }

    @PostMapping("/order")
    public String makeorder(@RequestParam(name = "client_name") String name,
                            @RequestParam(name = "product_name") String prodName,
                            @RequestParam(name = "paymentMethod") String paymentMethod,
                            @RequestParam(name = "deliveryMethod") String deliveryMethod,
                            @RequestParam(name = "paymentStatus") String paymentStatus,
                            @RequestParam(name = "orderStatus") String orderStatus) {

        Client client = clientService.getClientByName(name);
        Product product = productService.getProductByName(prodName);

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        PaymentMethod payMethod = PaymentMethod.valueOf(paymentMethod);
        DeliveryMethod delMethod = DeliveryMethod.valueOf(deliveryMethod);
        PaymentStatus payStatus = PaymentStatus.valueOf(paymentStatus);
        OrderStatus ordStatus = OrderStatus.valueOf(orderStatus);

        Order newOrder = new Order(client, productList, payMethod, delMethod, payStatus, ordStatus);
        orderService.saveOrders(newOrder);

        return "addorder";
    }
}