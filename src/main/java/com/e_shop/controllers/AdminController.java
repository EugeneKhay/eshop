package com.e_shop.controllers;

import com.e_shop.domain.Order;
import com.e_shop.domain.Product;
import com.e_shop.domain.ProductParameteres;
import com.e_shop.enums.ProductCategory;
import com.e_shop.services.ClientService;
import com.e_shop.services.OrderService;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/admin")
    public String view() {
        return "adminpage";
    }

    @PostMapping("/addproducts")
    public String addProduct(@RequestParam(name = "productName") String productName,
                       @RequestParam(name = "productPrice") double productPrice,
                       @RequestParam(name = "category") String category,
                       @RequestParam(name = "amount") int amount,
                       @RequestParam(name = "colour") String colour,
                       @RequestParam(name = "brand") String brand) {
        ProductParameteres productParameteres = new ProductParameteres(colour, brand);
        ProductCategory productCategory = ProductCategory.valueOf(category);
        Product product = new Product(productName, productPrice, productCategory, productParameteres, amount);
        productService.saveProduct(product);
        return "listofproducts";
    }

    @GetMapping("/viewproducts")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "listofproducts";
    }

    @GetMapping("/viewclients")
    public String showClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "listofclients";
    }

    @PostMapping("/statistics")
    public String getStats(@RequestParam(name = "startDay") String startDay,
                           @RequestParam(name = "startMonth") String startMonth,
                           @RequestParam(name = "startYear") String startYear,
                           @RequestParam(name = "finishDay") String finishDay,
                           @RequestParam(name = "finishMonth") String finishMonth,
                           @RequestParam(name = "finishYear") String finishYear,
                           Model model) {
        LocalDate start = orderService.getDate(startDay, startMonth, startYear);
        LocalDate finish = orderService.getDate(finishDay, finishMonth, finishYear);
        double totalSumOfAllOrders = orderService.getTotalSumOfAllOrdersPerPeriod(start, finish);
        long totalAmountOfOrders = orderService.getTotalAmountOfOrdersPerPeriod(start, finish);
        model.addAttribute("orders", orderService.getOrdersPerPeriod(start, finish));
        model.addAttribute("totalSumOfAllOrders", totalSumOfAllOrders);
        model.addAttribute("totalAmountOfOrders", totalAmountOfOrders);
        return "adminpage";
    }



}
