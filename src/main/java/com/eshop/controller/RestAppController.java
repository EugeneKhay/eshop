package com.eshop.controller;

import com.eshop.domain.Product;
import com.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controller class for REST requests handling.
 */
@RestController
public class RestAppController {

    @Autowired
    private OrderService orderService;

    /**
     * Handle the request to "/bestsellers" URL to send data about ten best products.
     * @return list of ten best products
     */
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