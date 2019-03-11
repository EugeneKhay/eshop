package com.e_shop.services.impl;

import com.e_shop.dao.ProductDAO;
import com.e_shop.domain.Product;
import com.e_shop.services.ProductService;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
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
    public List<Product> getAllProductsByPrice(double priceMin, double priceMax) {
        return dao.getAllProductsByPrice(priceMin, priceMax);
    }


    @Override
    public List<Product> getAllProductsByBrand(String brand) {
        return dao.getAllProductsByBrand(brand);
    }

    @Override
    public List<Product> getAllProductsByColour(String colour) {
        return dao.getAllProductsByColour(colour);
    }

    @Override
    public void saveProduct(Product product) {
        dao.saveProduct(product);
    }

}
