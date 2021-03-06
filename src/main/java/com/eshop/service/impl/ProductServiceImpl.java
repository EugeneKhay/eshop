package com.eshop.service.impl;

import com.eshop.dao.ProductDAO;
import com.eshop.domain.CategoryOfProduct;
import com.eshop.domain.Product;
import com.eshop.domain.ProductParameteres;
import com.eshop.jms.MessageSender;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;

/**
 * Contains implementations of methods ProductService for working with the product.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO dao;

    @Autowired
    private MessageSender sender;

    @Override
    public Product getProductByName(String name) {
        return dao.getProductByName(name);
    }

    @Override
    public Product getProductById(int id) {
        return dao.getProductById(id);
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
    public List<Product> getAllProductsByPrice(double priceMin, double priceMax, String type) {
        return dao.getAllProductsByPrice(priceMin, priceMax, type);
    }

    @Override
    public List<Product> getAllProductsByBrand(String brand) {
        return dao.getAllProductsByBrand(brand);
    }

    @Override
    public List<Product> getAllProductsByColour(String colour, String type) {
        return dao.getAllProductsByColour(colour, type);
    }

    @Override
    public void editProductByAdmin(int productId, String productName, String brand, double price, int amount, String category, String colour, int weight, String operatingSystem) {
        CategoryOfProduct productCategory = getSingleCategoryByName(category);
        ProductParameteres parameteres = new ProductParameteres(colour, brand, weight, operatingSystem);
        Product product = getProductById(productId);
        product.setProductName(productName);
        product.setProductPrice(price);
        product.setAmount(amount);
        product.setProductCategory(productCategory);
        product.setProductParameteres(parameteres);
        saveProduct(product);
        sender.sendMessage("Update");
    }

    @Override
    public Set<CategoryOfProduct> getAllCategories() {
        return dao.getAllCategories();
    }

    @Override
    public void saveCategory(CategoryOfProduct category) {
        dao.saveCategory(category);
    }

    @Override
    public List<CategoryOfProduct> getCategoryByName(String categoryName) {
        return dao.getCategoryByName(categoryName);
    }

    @Override
    public int deleteCategoryByName(String categoryName) {
        return dao.deleteCategoryByName(categoryName);
    }

    @Override
    public CategoryOfProduct getSingleCategoryByName(String name) {
        return dao.getSingleCategoryByName(name);
    }

    @Override
    public void saveProduct(Product product) {
        dao.saveProduct(product);
    }

    /**
     * Decreases amount of a product in stock.
     * @param product
     * @param countOfItems
     * @return
     */
    @Override
    public int decreaseProductAmountInStock(Product product, int countOfItems) {
        int newAmount;
        int amount = dao.getProductById(product.getId()).getAmount();
        if (amount >= countOfItems) {
           newAmount = amount - countOfItems;
        } else throw new IllegalArgumentException("Not enough amount");
        return newAmount;
    }

    @Override
    public int saveNewAmountOfProduct(Product product, int amount) {
        return dao.saveNewAmountOfProduct(product, amount);
    }

    @Override
    public List<Product> getAllProductsByCategory(CategoryOfProduct category) {
        return dao.getAllProductsByCategory(category);
    }
    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return dao.getAllProductsByCategory(category);
    }

    @Override
    public List<Product> getAllProductsByBrand(String brand, String type) {
        return dao.getAllProductsByBrand(brand, type);
    }

}
