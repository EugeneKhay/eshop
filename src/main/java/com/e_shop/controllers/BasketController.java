package com.e_shop.controllers;

import com.e_shop.domain.Basket;
import com.e_shop.domain.Product;
import com.e_shop.enums.ProductCategory;
import com.e_shop.services.ProductService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BasketController {

    @Autowired
    private ProductService productService;

    @GetMapping("/basket")
    public String getBasket(HttpServletRequest request, Model model) {
        Basket basket = (Basket) request.getSession().getAttribute("shop_basket");
        Map<Product, Integer> map = basket.getProductsInBasket();
        model.addAttribute("items", map);
        return "basket";
    }

    @PostMapping("/basket")
    public String addToBasket(@RequestParam(name = "item") int id,
                              @RequestParam(name = "page", required = false) String page,
                              HttpSession session,
                              Model model) {
        Product product = productService.getProductById(id);
        Basket basket = (Basket) session.getAttribute("shop_basket");

        if (basket.getProductsInBasket().containsKey(product)) {
            int amountInBasketBeforeAdd = basket.getProductsInBasket().get(product);
            basket.getProductsInBasket().put(product, amountInBasketBeforeAdd + 1);
        } else {
            basket.getProductsInBasket().put(product, 1);
        }
        session.setAttribute("shop_basket", basket);

        double totalPrice = 0;
        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
            totalPrice += (entry.getKey().getProductPrice() * entry.getValue());
        }
        session.setAttribute("totalPrice", totalPrice);

        ProductCategory category = product.getCategory();
        List<Product> products;
        //if came from homepage
        if (page == null) {
            products = productService.getAllProducts();
            model.addAttribute("items", products);
            return "homepage2";
        }
        // if came from any category of products
        products = productService.getAllProductsByCategory(category);
        model.addAttribute("items", products);
        return "products";
    }

    @GetMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Double> deleteFromBasketAjax(@RequestParam(name = "delete") int id,
                                   HttpSession session) {
        Product product = productService.getProductById(id);
        Basket basket = (Basket) session.getAttribute("shop_basket");
        Double totalPrice = (Double) session.getAttribute("totalPrice");
        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
            if (entry.getKey().equals(product))
                totalPrice = totalPrice - (entry.getKey().getProductPrice() * entry.getValue());
        }
        basket.getProductsInBasket().remove(product);
        session.setAttribute("shop_basket", basket);
        session.setAttribute("totalPrice", totalPrice);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    //TODO refactoring
    @GetMapping(value = "/editOrderMinus", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List> editOrderFromBasketMinusAjax(@RequestParam(name = "editOrderMinus") int id,
                                                             HttpSession session) {
        Product product = productService.getProductById(id);
        Basket basket = (Basket) session.getAttribute("shop_basket");
        double totalPrice = (double) session.getAttribute("totalPrice");

        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
            if (entry.getKey().equals(product))
                totalPrice = totalPrice - entry.getKey().getProductPrice();
        }
        int amountBefore = basket.getProductsInBasket().get(product);
        int amountAfter = --amountBefore;

        basket.getProductsInBasket().put(product, amountAfter);
        session.setAttribute("shop_basket", basket);
        session.setAttribute("totalPrice", totalPrice);

        List result = new ArrayList();
        result.add(totalPrice);
        result.add(amountAfter);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //TODO refactoring
    @GetMapping(value = "/editOrderPlus", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List> editOrderFromBasketPlusAjax(@RequestParam(name = "editOrderPlus") int id,
                                                             HttpSession session,
                                                             Model model) {

        Product product = productService.getProductById(id);
        Basket basket = (Basket) session.getAttribute("shop_basket");
        double totalPrice = (double) session.getAttribute("totalPrice");

        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
            if (entry.getKey().equals(product))
                totalPrice = totalPrice + entry.getKey().getProductPrice();
        }
        int amountBefore = basket.getProductsInBasket().get(product);
        int amountAfter = ++amountBefore;

        basket.getProductsInBasket().put(product, amountAfter);
        session.setAttribute("shop_basket", basket);
        session.setAttribute("totalPrice", totalPrice);

        List result = new ArrayList();
        result.add(totalPrice);
        result.add(amountAfter);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }




















    //    @PostMapping("/delete")
//    public String deleteFromBasket(@RequestParam(name = "delete") int id,
//                                   HttpSession session,
//                                   Model model) {
//        Product product = productService.getProductById(id);
//        Basket basket = (Basket) session.getAttribute("shop_basket");
//        double totalPrice = (double) session.getAttribute("totalPrice");
//        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
//            if (entry.getKey().equals(product))
//                totalPrice = totalPrice - (entry.getKey().getProductPrice() * entry.getValue());
//        }
//        basket.getProductsInBasket().remove(product);
//        session.setAttribute("shop_basket", basket);
//        session.setAttribute("totalPrice", totalPrice);
//        Map<Product, Integer> productsInBasket = basket.getProductsInBasket();
//        model.addAttribute("items", productsInBasket);
//        return "basket";
//    }


//    @PostMapping("/editOrderPlus")
//    public String editOrderFromBasketPlus(@RequestParam(name = "editOrderPlus") int id,
//                                          HttpSession session,
//                                          Model model) {
//        Product product = productService.getProductById(id);
//        Basket basket = (Basket) session.getAttribute("shop_basket");
//        double totalPrice = (double) session.getAttribute("totalPrice");
//
//        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
//            if (entry.getKey().equals(product))
//                totalPrice = totalPrice + entry.getKey().getProductPrice();
//        }
//        int amountBefore = basket.getProductsInBasket().get(product);
//        int amountAfter = ++amountBefore;
//
////        Basket basket2 = (Basket) session.getAttribute("shop_basket");
////        basket2.getProductsInBasket().put(product, amountAfter);
////        session.setAttribute("shop_basket", basket2);
////        session.setAttribute("totalPrice", totalPrice);
////
////        Map<Product, Integer> productsInBasket = basket2.getProductsInBasket();
////        model.addAttribute("items", productsInBasket);
////        return "basket";
//
//        //Basket basket2 = (Basket) session.getAttribute("shop_basket");
//        basket.getProductsInBasket().put(product, amountAfter);
//        session.setAttribute("shop_basket", basket);
//        session.setAttribute("totalPrice", totalPrice);
//
//        Map<Product, Integer> productsInBasket = basket.getProductsInBasket();
//        model.addAttribute("items", productsInBasket);
//        return "basket";
//    }
//
//    @PostMapping("/editOrderMinus")
//    public String editOrderFromBasketMinus(@RequestParam(name = "editOrderMinus") int id,
//                                           HttpSession session,
//                                           Model model) {
//        Product product = productService.getProductById(id);
//        Basket basket = (Basket) session.getAttribute("shop_basket");
//        double totalPrice = (double) session.getAttribute("totalPrice");
//
//        for (Map.Entry<Product, Integer> entry: basket.getProductsInBasket().entrySet()) {
//            if (entry.getKey().equals(product))
//                totalPrice = totalPrice - entry.getKey().getProductPrice();
//        }
//        int amountBefore = basket.getProductsInBasket().get(product);
//        int amountAfter = --amountBefore;
//
//        basket.getProductsInBasket().put(product, amountAfter);
//        session.setAttribute("shop_basket", basket);
//        session.setAttribute("totalPrice", totalPrice);
//
//        Map<Product, Integer> productsInBasket = basket.getProductsInBasket();
//        model.addAttribute("items", productsInBasket);
//        return "basket";
//    }
}

