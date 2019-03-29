package com.e_shop.controllers;

import com.e_shop.domain.Product;
import com.e_shop.enums.ProductCategory;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping
public class CatalogController {

    @Autowired
    private ProductService productService;

    @GetMapping("/catalog")
    public String getCatalog(@RequestParam(name = "page") String page,
                             Model model) {
        ProductCategory category = ProductCategory.valueOf(page.toUpperCase());
        List<Product> allProductsByCategory = productService.getAllProductsByCategory(category);
        model.addAttribute("items", allProductsByCategory);
        return "products";
    }

    @PostMapping("/phone")
    public String postCatalog(@RequestParam(name = "search_type") String filter,
                              @RequestParam(name = "search_res") String search_data,
                              @RequestParam(name = "page") String page,
                              Model model) {
        String productType = page.substring(1).toUpperCase();
        ProductCategory category = ProductCategory.valueOf(productType);
        List<Product> searchResult;
        if (filter.equals("Price")) {
            //TODO
            String[] arr = search_data.split(" ");
            double priceMin = Double.valueOf(arr[0]);
            double priceMax = Double.valueOf(arr[1]);
            searchResult = productService.getAllProductsByPrice(priceMin, priceMax);
        } else if (filter.equals("Brand")) {
            searchResult = productService.getAllProductsByBrand(search_data, productType);
        } else if (filter.equals("Colour")) {
            searchResult = productService.getAllProductsByColour(search_data, productType);
        } else {
            searchResult = productService.getAllProductsByCategory(category);
        }
        model.addAttribute("items", searchResult);
        return "products";
    }



}
