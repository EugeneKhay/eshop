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
    public String postCatalog(@RequestParam(name = "search_res11", required = false) Double search_dataPrice1,
                              @RequestParam(name = "search_res12", required = false) Double search_dataPrice2,
                              @RequestParam(name = "search_res2", required = false) String search_dataBrand,
                              @RequestParam(name = "search_res3", required = false) String search_dataColour,
                              @RequestParam(name = "page") String page,
                              Model model) {
        List<Product> dataForPostCatalog = catalogService.getDataForPostCatalog(search_dataPrice1, search_dataPrice2, search_dataBrand, search_dataColour, page);
        model.addAttribute("items", dataForPostCatalog);
        model.addAttribute("pageName", productService.getPageName(page));
        return "products";
    }
}

























//    @PostMapping("/phone")
//    public String postCatalog(@RequestParam(name = "search_res1", required = false) String search_dataPrice,
//                              @RequestParam(name = "search_res2", required = false) String search_dataBrand,
//                              @RequestParam(name = "search_res3", required = false) String search_dataColour,
//                              @RequestParam(name = "page") String page,
//                              Model model) {
//        List<Product> dataForPostCatalog = catalogService.getDataForPostCatalog(search_dataPrice, search_dataBrand, search_dataColour, page);
//        model.addAttribute("items", dataForPostCatalog);
//        model.addAttribute("pageName", productService.getPageName(page));
//        return "products";
//    }























