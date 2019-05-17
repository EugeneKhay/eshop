package com.eshop.controller;

import com.eshop.domain.Product;
import com.eshop.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controller class for catalog requests handling.
 */
@Controller
@RequestMapping
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    /**
     * Handle the search requests from user
     * @param search_dataPrice1 min price of the product
     * @param search_dataPrice2 max price of the product
     * @param search_dataBrand brand of the product
     * @param search_dataColour colour of the product
     * @param page category of the product.
     * @param model model for view
     * @return name of the corresponding view
     */
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