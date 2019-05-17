package com.eshop.service.impl;

import com.eshop.domain.CategoryOfProduct;
import com.eshop.domain.Product;
import com.eshop.service.CatalogService;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Contains methods for work with the catalog.
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private ProductService productService;

    /**
     * Filter the products of the particular category by price, brand, colour.
     * @param search_dataPrice1 min price
     * @param search_dataPrice2 max price
     * @param search_dataBrand brand of a product
     * @param search_dataColour colour of product
     * @param page name of the product category
     * @return list that contains filtered products
     */
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

    public CatalogServiceImpl(ProductService service) {
        this.productService = service;
    }
}
