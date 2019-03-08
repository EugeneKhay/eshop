package com.e_shop.controllers;

import com.e_shop.domain.Product;
import com.e_shop.domain.ProductParameteres;
import com.e_shop.enums.ProductCategory;
import com.e_shop.services.ClientService;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @GetMapping("/admin")
    public String view() {
        return "adminpage";
    }

    @PostMapping("/addproducts")
    public String add(@RequestParam(name = "productName") String productName,
                       @RequestParam(name = "productPrice") double productPrice,
                       @RequestParam(name = "category") String category,
                       @RequestParam(name = "amount") int amount,
                       @RequestParam(name = "colour") String colour,
                       @RequestParam(name = "brand") String brand) {
        ProductParameteres productParameteres = new ProductParameteres(colour, brand);
        ProductCategory productCategory = category.equals("electronic") ? ProductCategory.ELECTRONIC : ProductCategory.MECHANICAL;
        Product product = new Product(productName, productPrice, productCategory, productParameteres, amount);
        productService.saveProduct(product);
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

}
