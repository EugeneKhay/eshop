package com.e_shop.services;

import com.e_shop.domain.Product;
import com.e_shop.enums.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    Product getProductByName(String name);

    Product getProductById(int id);

    List<Product> getAllProducts();

    List<Product> getAllProductsByPrice(double priceMin, double priceMax);

    List<Product> getAllProductsByPrice(double priceMin, double priceMax, String type);

    List<Product> getAllProductsByBrand(String brand);

    void saveProduct(Product product);

    int decreaseProductAmountInStock(Product product, int countOfItems);

    int saveNewAmountOfProduct(Product product, int amount);

    List<Product> getAllProductsByCategory(ProductCategory category);

    List<Product> getAllProductsByBrand(String brand, String type);

    List<Product> getAllProductsByColour(String colour);

    List<Product> getAllProductsByColour(String colour, String type);

    String getPageName(String page);




}
