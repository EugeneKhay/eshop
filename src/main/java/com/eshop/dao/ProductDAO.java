package com.eshop.dao;

import com.eshop.domain.CategoryOfProduct;
import com.eshop.domain.Product;
import java.util.List;
import java.util.Set;

/**
 * Interface that contain methods for Product and CategoryOfProduct working with DB
 */
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

    List<Product> getAllProductsByCategory(CategoryOfProduct category);

    List<Product> getAllProductsByCategory(String category);

    void saveProduct(Product product);

    int saveNewAmountOfProduct(Product product, int amount);

    Set<CategoryOfProduct> getAllCategories();

    CategoryOfProduct getSingleCategoryByName(String name);

    void saveCategory(CategoryOfProduct category);

    List<CategoryOfProduct> getCategoryByName(String categoryName);

    int deleteCategoryByName(String categoryName);
}