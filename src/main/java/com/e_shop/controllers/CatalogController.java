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
            //FIX ME
            String[] arr = search_data.split(" ");
            double priceMin = Double.valueOf(arr[0]);
            double priceMax = Double.valueOf(arr[1]);
            searchResult = productService.getAllProductsByPrice(priceMin, priceMax);
        } else if (filter.equals("Brand")) {
            searchResult = productService.getAllProductsByBrand(search_data);
        } else if (filter.equals("Colour")) {
            searchResult = productService.getAllProductsByColour(search_data);
        }
        model.addAttribute("products", searchResult);
        return "catalog";
    }
}
