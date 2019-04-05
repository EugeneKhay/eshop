package com.e_shop.services.impl;

import com.e_shop.domain.Product;
import com.e_shop.enums.ProductCategory;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private ProductService productService;

    public List<Product> getDataForPostCatalog(String search_dataPrice, String search_dataBrand,
                                               String search_dataColour, String page) {
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
        return searchResult;
    }
}
