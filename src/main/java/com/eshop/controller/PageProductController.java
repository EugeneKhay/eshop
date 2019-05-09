//package com.eshop.controller;
//
//import com.eshop.domain.CategoryOfProduct;
//import com.eshop.domain.Product;
//import com.eshop.enums.ProductCategory;
//import com.eshop.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//
//@Controller
//public class PageProductController {
//
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping("/{category}")
//    public String get(@PathVariable(name = "category") String cat, Model model) {
//        String end = cat.substring(1);
//        String start = cat.substring(0,1).toUpperCase();
//        String res = start + end;
//
//        CategoryOfProduct category = new CategoryOfProduct(res);
//        List<Product> allProductsByCategory = productService.getAllProductsByCategory(cat);
//        model.addAttribute("items", allProductsByCategory);
//        model.addAttribute("pageName", category.getCategoryName());
//
//        return "products";
//    }
//}
