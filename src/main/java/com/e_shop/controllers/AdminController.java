package com.e_shop.controllers;

import com.e_shop.services.ClientService;
import com.e_shop.services.ProductService;
import com.e_shop.services.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.logging.Logger;


@Controller
public class AdminController {

    private Logger logger = Logger.getLogger("logger");

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String view(Model model) {
        logger.info("Go to adminpage");
        LocalDate start = LocalDate.of(2019, 1, 1);
        LocalDate finish = LocalDate.now();
        adminService.setStats(model, start, finish);
        return "adminpage";
    }

    @PostMapping("/addproducts")
    public String addProduct(@RequestParam(name = "productName") String productName,
                             @RequestParam(name = "productPrice") double productPrice,
                             @RequestParam(name = "category") String category,
                             @RequestParam(name = "amount") int amount,
                             @RequestParam(name = "colour") String colour,
                             @RequestParam(name = "brand") String brand,
                             @RequestParam(name = "image") String image) {
        adminService.addNewProduct(productName, productPrice, category, amount, colour, brand, image);
        return "adminpage";
    }

    @GetMapping("/viewproducts")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "adminpage";
    }

    @GetMapping("/viewclients")
    public String showClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "adminpage";
    }

    @PostMapping("/admin")
    public String getStats(@RequestParam(name = "start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate startParameter,
                           @RequestParam(name = "finish", required = false)@DateTimeFormat(pattern="yyyy-MM-dd")LocalDate finishParameter,
                           Model model) {
        LocalDate start = adminService.getStartDate(startParameter);
        LocalDate finish = adminService.getFinishDate(finishParameter);
        adminService.setStats(model, start, finish);
        return "adminpage";
    }
}






























//    @PostMapping("/statistics")
//    public String getStats(@RequestParam(name = "startDay") String startDay,
//                           @RequestParam(name = "startMonth") String startMonth,
//                           @RequestParam(name = "startYear") String startYear,
//                           @RequestParam(name = "finishDay") String finishDay,
//                           @RequestParam(name = "finishMonth") String finishMonth,
//                           @RequestParam(name = "finishYear") String finishYear,
//                           Model model) {
//        LocalDate start = orderService.getDate(startDay, startMonth, startYear);
//        LocalDate finish = orderService.getDate(finishDay, finishMonth, finishYear);
//        double totalSumOfAllOrders = orderService.getTotalSumOfAllOrdersPerPeriod(start, finish);
//        long totalAmountOfOrders = orderService.getTotalAmountOfOrdersPerPeriod(start, finish);
//        model.addAttribute("bestClient", clientService.getTenBestClientsPerPeriod(start, finish));
//        model.addAttribute("bestProducts", orderService.getBestsellerPerPeriod(start, finish));
//        model.addAttribute("orders", orderService.getOrdersPerPeriod(start, finish));
//        model.addAttribute("totalSumOfAllOrders", totalSumOfAllOrders);
//        model.addAttribute("totalAmountOfOrders", totalAmountOfOrders);
//        return "adminpage";
//    }


//    @PostMapping("/statistics")
//    public String getStats(@RequestParam(name = "start") @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate start,
//                           @RequestParam(name = "finish")@DateTimeFormat(pattern="yyyy-MM-dd") LocalDate finish,
//                           Model model) {
//        double totalSumOfAllOrders = orderService.getTotalSumOfAllOrdersPerPeriod(start, finish);
//        long totalAmountOfOrders = orderService.getTotalAmountOfOrdersPerPeriod(start, finish);
//        model.addAttribute("bestClient", clientService.getTenBestClientsPerPeriod(start, finish));
//        model.addAttribute("bestProducts", orderService.getBestsellerPerPeriod(start, finish));
//        model.addAttribute("orders", orderService.getOrdersPerPeriod(start, finish));
//        model.addAttribute("totalSumOfAllOrders", totalSumOfAllOrders);
//        model.addAttribute("totalAmountOfOrders", totalAmountOfOrders);
//
//        model.addAttribute("products", productService.getAllProducts());
//        model.addAttribute("clients", clientService.getAllClients());
//
//        return "adminpage";
//    }