package com.e_shop.services.impl;

import com.e_shop.dao.ProductDAO;
import com.e_shop.domain.Product;
import com.e_shop.services.ProductService;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO dao;

    @Override
    public Product getProductByName(String name) {
        return dao.getProductByName(name);
    }

    @Override
    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }

    @Override
    public List<Product> getAllProductsByPrice(int price) {
        return dao.getAllProductsByPrice(price);
    }

    @Override
    public void saveProduct(Product product) {
        dao.saveProduct(product);
    }

}
