package com.eshop.controller;

import com.eshop.domain.Basket;
import com.eshop.domain.Client;
import com.eshop.domain.Product;
import com.eshop.service.ClientService;
import com.eshop.service.OrderService;
import com.eshop.service.impl.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.logging.Logger;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    private Logger logger = Logger.getLogger("logger");

    @GetMapping("/basket")
    public String getBasket(HttpServletRequest request, Model model) {
        Map<Product, Integer> productsInBasket = basketService.getProductsInBasket(request);
        model.addAttribute("items", productsInBasket);
        try {
            // TODO make separate method
//            Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            Integer id = client.getId();
//            Client clientForView = clientService.getClientById(id);

            Client clientForView = clientService.getClientForView();
            model.addAttribute("addresses", clientForView.getAddressList());
            //model.addAttribute("client", client);
            model.addAttribute("client", clientForView);
            model.addAttribute("addressesSelf", orderService.getAllShops());
        } catch (ClassCastException ex) {
            logger.info("Not authorized attempt go to basket page");
        }
        logger.info("Go to basket page");
        return "basket";
    }

    @GetMapping(value = "/basket", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Integer> addToBasketAjax(@RequestParam(name = "item") int id,
                                                   HttpSession session) {
        Integer shop_basket = basketService.prepareProductsForBasket(id, session);
        return new ResponseEntity<>(shop_basket, HttpStatus.OK);
    }

    @GetMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Double> deleteFromBasketAjax(@RequestParam(name = "delete") int id,
                                                       HttpSession session) {
        Double totalPrice = basketService.deleteFromBAsket(id, session);
        logger.info("Product deleted from basket completely");
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    @GetMapping(value = "/editOrderMinus", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List> editOrderFromBasketMinusAjax(@RequestParam(name = "editOrderMinus") int id,
                                                             HttpSession session) {
        List<Number> result = basketService.editOrderMinus(id, session);
        logger.info("Item of product removed from basket");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/editOrderPlus", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List> editOrderFromBasketPlusAjax(@RequestParam(name = "editOrderPlus") int id,
                                                            HttpSession session) {
        List<Number> result = basketService.editOrderPlus(id, session);
        logger.info("Item of product added to basket");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //TODO remove
    @PostMapping("/addaddress")
    public String addClientAddress (@RequestParam(name = "country", required = false) String country,
                                    @RequestParam(name = "city", required = false) String city,
                                    @RequestParam(name = "postcode", required = false) int postcode,
                                    @RequestParam(name = "street", required = false) String street,
                                    @RequestParam(name = "houseNumber", required = false) int houseNumber,
                                    @RequestParam(name = "flatNumber", required = false) int flatNumber,
                                    Model model,
                                    HttpServletRequest request) {
        Client clientForView = clientService.createAddressForClient(country, city, postcode, street, houseNumber, flatNumber);
        logger.info("Save address");
        model.addAttribute("client", clientForView);
        model.addAttribute("addresses", clientForView.getAddressList());
        //getBasket(request, model);
        return "personal";
    }
}




//    @PostMapping("/basket")
//    public String addToBasket(@RequestParam(name = "item") int id,
//                              @RequestParam(name = "page", required = false) String page,
//                              HttpSession session,
//                              Model model) {
//        List<Product> products = basketService.prepareProductsForBasket(id, page, session);
//        model.addAttribute("items", products);
//        if (page == null) {
//            logger.info("Product added to basket");
//            return "homepage2";
//        }
//        logger.info("Product added to basket");
//        return "products";
//
//    }

//    @PostMapping("/basket")
//    public void addToBasket(@RequestParam(name = "item") int id,
//                            @RequestParam(name = "page", required = false) String page,
//                            HttpSession session,
//                            HttpServletRequest request,
//                            Model model) {
//        basketService.prepareProductsForBasket(id, page, session);
//        getBasket(request, model);
//
//    }

//    @PostMapping("/basket")
//    public String addToBasket(@RequestParam(name = "item") int id,
//                              @RequestParam(name = "page", required = false) String page,
//                              HttpSession session
////                            HttpServletRequest request,
////                            Model model
//    ) {
//        basketService.prepareProductsForBasket(id, page, session);
//        if (page == null) return "redirect:/";
//        return "redirect:" + page;
//
//    }

