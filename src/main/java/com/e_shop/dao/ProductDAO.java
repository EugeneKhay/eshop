package com.e_shop.dao;

import com.e_shop.domain.Client;
import com.e_shop.domain.Product;

import java.util.List;

public interface ProductDAO {

    Product getProductByName(String name);

    List<Product> getAllProducts();

    void saveProduct(Product product);

}
