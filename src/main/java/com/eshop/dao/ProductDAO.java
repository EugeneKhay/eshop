package com.eshop.dao;

import com.eshop.domain.Product;
import com.eshop.enums.ProductCategory;

import java.util.List;

public interface ProductDAO {

    Product getProductByName(String name);

    Product getProductById(int id);

    List<Product> getAllProducts();

    List<Product> getAllProductsByPrice(double priceMin, double priceMax);

    List<Product> getAllProductsByPrice(double priceMin, double priceMax, String type);

    List<Product> getAllProductsByBrand(String brand);

    List<Product> getAllProductsByBrand(String brand, String type);

    List<Product> getAllProductsByColour(String colour);

    List<Product> getAllProductsByColour(String colour, String type);

    List<Product> getAllProductsByCategory(ProductCategory category);

    void saveProduct(Product product);

    int saveNewAmountOfProduct(Product product, int amount);

    String getPageName(String page);







}
