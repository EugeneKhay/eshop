package com.e_shop.controllers;

import com.e_shop.domain.Product;
import com.e_shop.enums.ProductCategory;
import com.e_shop.services.ProductService;
import com.e_shop.services.impl.CatalogService;
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

    @Autowired
    private CatalogService catalogService;

    @PostMapping("/phone")
    public String postCatalog(@RequestParam(name = "search_res1", required = false) String search_dataPrice,
                              @RequestParam(name = "search_res2", required = false) String search_dataBrand,
                              @RequestParam(name = "search_res3", required = false) String search_dataColour,
                              @RequestParam(name = "page") String page,
                              Model model) {
        List<Product> dataForPostCatalog = catalogService.getDataForPostCatalog(search_dataPrice, search_dataBrand, search_dataColour, page);
        model.addAttribute("items", dataForPostCatalog);
        model.addAttribute("pageName", productService.getPageName(page));
        return "products";
    }
}






















//    @GetMapping("/catalog")
//    public String getCatalog(@RequestParam(name = "page") String page,
//                             Model model) {
//        ProductCategory category = ProductCategory.valueOf(page.toUpperCase());
//        List<Product> allProductsByCategory = productService.getAllProductsByCategory(category);
//        model.addAttribute("items", allProductsByCategory);
//        model.addAttribute("pageName", getPageName(page));
//        return "products";
//    }



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
