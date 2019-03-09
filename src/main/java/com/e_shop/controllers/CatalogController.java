package com.e_shop.controllers;

import com.e_shop.domain.Product;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String getCatalog() {
        return "catalog";
    }

    @PostMapping
    public String postCatalog(@RequestParam(name = "search_type") String filter,
                              @RequestParam(name = "search_res") String search_data,
                              Model model) {
        List<Product> searchResult = new ArrayList<>();
        if (filter.equals("Price")) {
            int price = Integer.valueOf(search_data);
            searchResult = productService.getAllProductsByPrice(price);
//        } else if (filter.equals("Brand")) {
//            searchResult = productService.getAllProductsByBrand();
//        } else if (filter.equals("Colour")) {
//            searchResult = productService.getAllProductsByColour();
        }
        model.addAttribute("products", searchResult);
        System.out.println(searchResult.size());
        System.out.println(searchResult.get(0).getId());
        System.out.println(searchResult.get(0).getProductName());
        System.out.println(searchResult.get(0).getProductPrice());
        System.out.println(searchResult.get(0).getAmount());
        System.out.println(searchResult.get(0).getCategory());
        System.out.println(searchResult.get(0).getProductParameteres().getColour());
        System.out.println(searchResult.get(0).getProductParameteres().getBrand());
        return "catalog";
    }
}
