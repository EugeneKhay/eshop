package com.e_shop.controllers;

import com.e_shop.domain.Product;
import com.e_shop.services.impl.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    @GetMapping("/basket")
    public String getBasket(HttpServletRequest request, Model model) {
        Map<Product, Integer> productsInBasket = basketService.getProductsInBasket(request);
        model.addAttribute("items", productsInBasket);
        return "basket";
    }

    @PostMapping("/basket")
    public String addToBasket(@RequestParam(name = "item") int id,
                              @RequestParam(name = "page", required = false) String page,
                              HttpSession session,
                              Model model) {
        List<Product> products = basketService.prepareProductsForBasket(id, page, session);
        model.addAttribute("items", products);
        if (page == null) {
            return "homepage2";
        }
        return "products";
    }

    @GetMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Double> deleteFromBasketAjax(@RequestParam(name = "delete") int id,
                                                       HttpSession session) {
        Double totalPrice = basketService.deleteFromBAsket(id, session);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    @GetMapping(value = "/editOrderMinus", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List> editOrderFromBasketMinusAjax(@RequestParam(name = "editOrderMinus") int id,
                                                             HttpSession session) {
        List<Number> result = basketService.editOrderMinus(id, session);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/editOrderPlus", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List> editOrderFromBasketPlusAjax(@RequestParam(name = "editOrderPlus") int id,
                                                            HttpSession session) {
        List<Number> result = basketService.editOrderPlus(id, session);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

