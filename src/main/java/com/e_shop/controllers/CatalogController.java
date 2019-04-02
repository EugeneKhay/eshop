package com.e_shop.controllers;

import com.e_shop.domain.Product;
import com.e_shop.enums.ProductCategory;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
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

//    @PostMapping("/phone")
//    public String postCatalog(@RequestParam(name = "search_type", required = false)  String filter,
//                              @RequestParam(name = "search_res", required = false) String search_data,
//                              @RequestParam(name = "page") String page,
//                              Model model) {
//        List<Product> searchResult;
//        String productType = page.substring(1).toUpperCase();
//        ProductCategory category = ProductCategory.valueOf(productType);
//
//        if (filter == null || filter.isEmpty() || search_data == null || search_data.isEmpty()) {
//            searchResult = productService.getAllProductsByCategory(category);
//            model.addAttribute("items", searchResult);
//            return "products";
//        }
//        if (filter.equals("Price")) {
//            //TODO
//            String[] arr = search_data.split(" ");
//            double priceMin = Double.valueOf(arr[0]);
//            double priceMax = Double.valueOf(arr[1]);
//            searchResult = productService.getAllProductsByPrice(priceMin, priceMax, productType);
//        } else if (filter.equals("Brand")) {
//            searchResult = productService.getAllProductsByBrand(search_data, productType);
//        } else if (filter.equals("Colour")) {
//            searchResult = productService.getAllProductsByColour(search_data, productType);
//        }
//        else {
//            searchResult = productService.getAllProductsByCategory(category);
//        }
//        model.addAttribute("items", searchResult);
//        return "products";
//    }

    @PostMapping("/phone")
    public String postCatalog(@RequestParam(name = "search_type1", required = false)  String filterPrice,
                              @RequestParam(name = "search_res1", required = false) String search_dataPrice,
                              @RequestParam(name = "search_type2", required = false)  String filterBrand,
                              @RequestParam(name = "search_res2", required = false) String search_dataBrand,
                              @RequestParam(name = "search_type3", required = false)  String filterColour,
                              @RequestParam(name = "search_res3", required = false) String search_dataColour,
                              @RequestParam(name = "page") String page,
                              Model model) {
        String productType = page.substring(1).toUpperCase();
        ProductCategory category = ProductCategory.valueOf(productType);

        List<Product> searchResult = productService.getAllProductsByCategory(category);

        if (search_dataPrice != null && !search_dataPrice.isEmpty()) {
            String[] arr = search_dataPrice.split(" ");
            double priceMin = Double.valueOf(arr[0]);
            double priceMax = Double.valueOf(arr[1]);
            List<Product> filteredByPrice = productService.getAllProductsByPrice(priceMin, priceMax, productType);
            searchResult.retainAll(filteredByPrice);
        }
        if (search_dataBrand != null && !search_dataBrand.isEmpty()) {
            List<Product> filteredByBrand = productService.getAllProductsByBrand(search_dataBrand, productType);
            searchResult.retainAll(filteredByBrand);
        }
        if (search_dataColour != null && !search_dataColour.isEmpty()) {
            List<Product> filteredByColour = productService.getAllProductsByColour(search_dataColour, productType);
            searchResult.retainAll(filteredByColour);
        }
        model.addAttribute("items", searchResult);
        return "products";
    }

}
