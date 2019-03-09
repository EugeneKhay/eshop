package com.e_shop.services;

import com.e_shop.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    Product getProductByName(String name);

    List<Product> getAllProducts();

    List<Product> getAllProductsByPrice(int price);

    void saveProduct(Product product);


}
