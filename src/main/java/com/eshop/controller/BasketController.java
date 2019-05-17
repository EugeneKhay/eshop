package com.eshop.controller;

import com.eshop.domain.Client;
import com.eshop.domain.Product;
import com.eshop.service.BasketService;
import com.eshop.service.ClientService;
import com.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Controller class for basket requests handling.
 */
@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ClientService clientService;

    private Logger logger = Logger.getLogger("logger");

    /**
     * Handle the request to "/basket" URL to go to the basket page.
     * @param model model for view displaying
     * @return name of the corresponding view
     */
    @GetMapping("/basket")
    public String getBasket(HttpServletRequest request, Model model) {
        Map<Product, Integer> productsInBasket = basketService.getProductsInBasket(request);
        model.addAttribute("items", productsInBasket);
        try {
            Client clientForView = clientService.getClientForView();
            model.addAttribute("addresses", clientForView.getAddressList());
            model.addAttribute("client", clientForView);
            model.addAttribute("addressesSelf", orderService.getAllShops());
        } catch (ClassCastException ex) {
            logger.info("Not authorized attempt to go to the basket page");
        }
        logger.info("Go to basket page");
        return "basket";
    }

    /**
     * Handle the AJAX request to "/basket" URL to add one item of the particular product
     * to the basket.
     * @param id id of the product to add
     * @param session current HTTP session
     * @return total amount of all product items in a basket
     */
    @GetMapping(value = "/basket", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Integer> addToBasketAjax(@RequestParam(name = "item") int id,
                                                   HttpSession session) {
        Integer shop_basket = basketService.prepareProductsForBasket(id, session);
        return new ResponseEntity<>(shop_basket, HttpStatus.OK);
    }

    /**
     * Handle the AJAX request to "/delete" URL to remove all items of the particular product
     * from the basket.
     * @param id id of the product to remove
     * @param session current HTTP session
     * @return total price of all products in a basket
     */
    @GetMapping(value = "/delete", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<Double> deleteFromBasketAjax(@RequestParam(name = "delete") int id,
                                                       HttpSession session) {
        Double totalPrice = basketService.deleteFromBAsket(id, session);
        logger.info("Product deleted from basket completely");
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }

    /**
     * Handle the AJAX request to "/editOrderMinus" URL to remove one item of the particular product
     * from the basket.
     * @param id id of the product to remove
     * @param session current HTTP session
     * @return list of numbers that contains new total price and new amount of particular product in the basket
     */
    @GetMapping(value = "/editOrderMinus", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List> editOrderFromBasketMinusAjax(@RequestParam(name = "editOrderMinus") int id,
                                                             HttpSession session) {
        List<Number> result = basketService.editOrderMinus(id, session);
        logger.info("Item of product removed from basket");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Handle the AJAX request to "/editOrderMinus" URL to add one item of the particular product
     * to the basket.
     * @param id id of the product to remove
     * @param session current HTTP session
     * @return list of numbers that contains new total price and new amount of particular product in the basket
     */
    @GetMapping(value = "/editOrderPlus", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<List> editOrderFromBasketPlusAjax(@RequestParam(name = "editOrderPlus") int id,
                                                            HttpSession session) {
        List<Number> result = basketService.editOrderPlus(id, session);
        logger.info("Item of product added to basket");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}






