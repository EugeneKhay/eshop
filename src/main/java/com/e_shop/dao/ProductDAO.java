package com.e_shop.dao;

import com.e_shop.domain.Client;
import com.e_shop.domain.Product;

import java.util.List;

public interface ProductDAO {

    Product getProductByName(String name);

    Product getProductById(int id);

    List<Product> getAllProducts();

    List<Product> getAllProductsByPrice(double priceMin, double priceMax);

    List<Product> getAllProductsByBrand(String brand);

    List<Product> getAllProductsByColour(String colour);

    void saveProduct(Product product);

    int saveNewAmountOfProduct(Product product, int amount);

}
