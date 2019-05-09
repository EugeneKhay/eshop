package com.eshop.controller;

import com.eshop.domain.Product;
import com.eshop.enums.ProductCategory;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/phone")
    public String phones(Model model) {

        List<Product> phones = productService.getAllProductsByCategory(ProductCategory.PHONE);
        model.addAttribute("items", phones);
        model.addAttribute("pageName", "Phones");
        return "products";
    }

    @GetMapping("/tv")
    public String tv(Model model) {
        List<Product> tv = productService.getAllProductsByCategory(ProductCategory.TV_VIDEO);
        model.addAttribute("items", tv);
        model.addAttribute("pageName", "TV & Video");
        return "products";
    }

    @GetMapping("/audio")
    public String audio(Model model) {
        List<Product> audio = productService.getAllProductsByCategory(ProductCategory.AUDIO);
        model.addAttribute("items", audio);
        model.addAttribute("pageName", "Audio & Hi-Fi");
        return "products";
    }

    @GetMapping("/laptop")
    public String laptops(Model model) {
        List<Product> laptops = productService.getAllProductsByCategory(ProductCategory.LAPTOP);
        model.addAttribute("items", laptops);
        model.addAttribute("pageName", "Laptops");
        return "products";
    }

    @GetMapping("/tablet")
    public String tablets(Model model) {
        List<Product> tablets = productService.getAllProductsByCategory(ProductCategory.TABLET);
        model.addAttribute("items", tablets);
        model.addAttribute("pageName", "Tablets");
        return "products";
    }
}
