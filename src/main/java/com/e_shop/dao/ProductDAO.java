package com.e_shop.dao;

import com.e_shop.domain.Client;
import com.e_shop.domain.Product;

import java.util.List;

public interface ProductDAO {

    Product getProductByName(String name);

    List<Product> getAllProducts();

    List<Product> getAllProductsByPrice(int price);

//    List<Product> getAllProductsByBrand(String brand);
//
//    List<Product> getAllProductsByColour();

    void saveProduct(Product product);

}
