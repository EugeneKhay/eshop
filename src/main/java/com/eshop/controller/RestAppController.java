package com.eshop.controller;

import com.eshop.domain.Product;
import com.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RestAppController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value= "/bestsellers", method = RequestMethod.GET)
    public List<Product> getMyData() {
        List<Product> bestsellers = orderService.getBestsellers();
        for (Product p: bestsellers) {
            String[] split = p.getImagePath().split("/");
            p.setImagePath(split[split.length-1]);
        }
        return bestsellers;
    }

    public RestAppController(OrderService orderService) {
        this.orderService = orderService;
    }
}