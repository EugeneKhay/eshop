package com.e_shop.controllers;

import com.e_shop.domain.Product;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/phones")
    public String phones(Model model) {
        //FIX ME
        List<Product> phones = productService.getAllProducts();
        model.addAttribute("phones", phones);
        return "phones";
    }
}
