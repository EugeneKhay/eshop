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

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
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

//    @GetMapping("/order")
//    public String order() {
//        return "addorder";
//    }

    @GetMapping("/orders")
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "listoforders";
    }

//    @PostMapping("/order")
//    public String makeorder(@RequestParam(name = "client_name") String name,
//                            @RequestParam(name = "product_name") String prodName,
//                            @RequestParam(name = "paymentMethod") String paymentMethod,
//                            @RequestParam(name = "deliveryMethod") String deliveryMethod,
//                            @RequestParam(name = "paymentStatus") String paymentStatus,
//                            @RequestParam(name = "orderStatus") String orderStatus
//                            ) {
//
//        Client client = clientService.getClientByName(name);
//        Product product = productService.getProductByName(prodName);
//
//        List<Product> productList = new ArrayList<>();
//        productList.add(product);
//
//        PaymentMethod payMethod = PaymentMethod.valueOf(paymentMethod);
//        DeliveryMethod delMethod = DeliveryMethod.valueOf(deliveryMethod);
//        PaymentStatus payStatus = PaymentStatus.valueOf(paymentStatus);
//        OrderStatus ordStatus = OrderStatus.valueOf(orderStatus);
//
//        Order newOrder = new Order(client, productList, payMethod, delMethod, payStatus, ordStatus);
//        orderService.saveOrders(newOrder);
//
//        return "addorder";
//    }

    @PostMapping("/confirm")
    public String confirmOrder(HttpSession session,
                               //Model model,
                               @RequestParam(name = "paymentMethod") String paymentMethod,
                               @RequestParam(name = "deliveryMethod") String deliveryMethod)
                               //@RequestParam(name = "amount") int amountToBuy)
    {
        Order order = new Order();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Client client = (Client) auth.getPrincipal();
        Basket basket = (Basket) session.getAttribute("shop_basket");
        List<Product> productList = basket.getProductList();

        order.setClient(client);
        order.setProductsInOrder(productList);
        order.setDeliveryMethod(DeliveryMethod.valueOf(deliveryMethod));
        order.setPaymentMethod(PaymentMethod.valueOf(paymentMethod));
        order.setDateOfOrder(LocalDate.now());

        orderService.saveOrders(order);

        for (Product product: productList) {
            int amount = productService.decreaseProductAmountInStock(product, 1);
            productService.saveNewAmountOfProduct(product, amount);
        }
                System.out.println(order);
        return "homepage2";
    }

    @PostMapping("/editorder")
    public String editOrderByAdmin(@RequestParam(name = "orderForEdit") int id,
                            @RequestParam(name = "paymentStatus") String paymentStatus,
                            @RequestParam(name = "orderStatus") String orderStatus,
                            Model model)
    {
        Order orderForEditing = orderService.getOrderById(id);
        orderForEditing.setPaymentStatus(PaymentStatus.valueOf(paymentStatus));
        orderForEditing.setOrderStatus(OrderStatus.valueOf(orderStatus));

        orderService.updateOrder(id, paymentStatus, orderStatus);

        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
                System.out.println(id);
        return "listoforders";
    }
}
