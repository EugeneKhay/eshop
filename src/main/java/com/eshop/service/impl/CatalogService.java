package com.eshop.service.impl;

import com.eshop.domain.CategoryOfProduct;
import com.eshop.domain.Product;
import com.eshop.enums.ProductCategory;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatalogService {

    @Autowired
    private ProductService productService;

    public List<Product> getDataForPostCatalog(Double search_dataPrice1, Double search_dataPrice2,
                                               String search_dataBrand,
                                               String search_dataColour,
                                               String page) {
        List<Product> searchResult = productService.getAllProductsByCategory(page);
        if (search_dataPrice1 != null && search_dataPrice2 != null) {
            double priceMin = search_dataPrice1;
            double priceMax = search_dataPrice2;
            List<Product> filteredByPrice = productService.getAllProductsByPrice(priceMin, priceMax, page);
            searchResult.retainAll(filteredByPrice);
        }
        if (search_dataBrand != null && !search_dataBrand.isEmpty()) {
            List<Product> filteredByBrand = productService.getAllProductsByBrand(search_dataBrand, page);
            searchResult.retainAll(filteredByBrand);
        }
        if (search_dataColour != null && !search_dataColour.isEmpty()) {
            List<Product> filteredByColour = productService.getAllProductsByColour(search_dataColour, page);
            searchResult.retainAll(filteredByColour);
        }
        return searchResult;
    }

    public CategoryOfProduct getCategoryByPage(String page) {
        String start = page.substring(0,1).toUpperCase();
        String end = page.substring(1);
        String res = start + end;
        CategoryOfProduct category = new CategoryOfProduct(res);
        return category;
    }

    public CatalogService(ProductService productService) {
        this.productService = productService;
    }
}








//    public List<Product> getDataForPostCatalog(String search_dataPrice, String search_dataBrand,
//                                               String search_dataColour, String page) {
//        String productType = page.substring(1).toUpperCase();
//        ProductCategory category = ProductCategory.valueOf(productType);
//
//        List<Product> searchResult = productService.getAllProductsByCategory(category);
//
//        if (search_dataPrice != null && !search_dataPrice.isEmpty()) {
//            String[] arr = search_dataPrice.split(" ");
//            double priceMin = Double.valueOf(arr[0]);
//            double priceMax = Double.valueOf(arr[1]);
//            List<Product> filteredByPrice = productService.getAllProductsByPrice(priceMin, priceMax, productType);
//            searchResult.retainAll(filteredByPrice);
//        }
//        if (search_dataBrand != null && !search_dataBrand.isEmpty()) {
//            List<Product> filteredByBrand = productService.getAllProductsByBrand(search_dataBrand, productType);
//            searchResult.retainAll(filteredByBrand);
//        }
//        if (search_dataColour != null && !search_dataColour.isEmpty()) {
//            List<Product> filteredByColour = productService.getAllProductsByColour(search_dataColour, productType);
//            searchResult.retainAll(filteredByColour);
//        }
//        return searchResult;
//    }
