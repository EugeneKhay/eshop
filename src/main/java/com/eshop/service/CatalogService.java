package com.eshop.service;

import com.eshop.domain.Product;

import java.util.List;

public interface CatalogService {

    List<Product> getDataForPostCatalog(Double search_dataPrice1, Double search_dataPrice2,
                                               String search_dataBrand, String search_dataColour, String page);
}
