package com.eshop.controller;

import com.eshop.domain.Product;
import com.eshop.service.impl.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @PostMapping("/{page}")
    public String postCatalog(@RequestParam(name = "search_res11", required = false) Double search_dataPrice1,
                              @RequestParam(name = "search_res12", required = false) Double search_dataPrice2,
                              @RequestParam(name = "search_res2", required = false) String search_dataBrand,
                              @RequestParam(name = "search_res3", required = false) String search_dataColour,
                              @PathVariable(name = "page") String page,
                              Model model) {
        List<Product> dataForPostCatalog = catalogService.getDataForPostCatalog(search_dataPrice1, search_dataPrice2, search_dataBrand, search_dataColour, page);
        model.addAttribute("items", dataForPostCatalog);
        model.addAttribute("pageName", page);
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























