package com.e_shop.controllers;

import com.e_shop.domain.Product;
import com.e_shop.enums.ProductCategory;
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

        List<Product> phones = productService.getAllProductsByCategory(ProductCategory.PHONE);
        model.addAttribute("items", phones);
        return "products";
    }

    @GetMapping("/tv")
    public String tv(Model model) {
        List<Product> tv = productService.getAllProductsByCategory(ProductCategory.TV_VIDEO);
        model.addAttribute("items", tv);
        return "products";
    }

    @GetMapping("/audio")
    public String audio(Model model) {
        List<Product> audio = productService.getAllProductsByCategory(ProductCategory.AUDIO);
        model.addAttribute("items", audio);
        return "products";
    }

    @GetMapping("/laptops")
    public String laptops(Model model) {
        List<Product> laptops = productService.getAllProductsByCategory(ProductCategory.LAPTOP);
        model.addAttribute("items", laptops);
        return "products";
    }

    @GetMapping("/tablets")
    public String tablets(Model model) {
        List<Product> tablets = productService.getAllProductsByCategory(ProductCategory.TABLET);
        model.addAttribute("items", tablets);
        return "products";
    }
}
