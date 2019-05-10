package com.eshop.dao;

import com.eshop.domain.CategoryOfProduct;
import com.eshop.domain.Product;
import com.eshop.enums.ProductCategory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

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

    //List<Product> getAllProductsByCategory(ProductCategory category);

    List<Product> getAllProductsByCategory(CategoryOfProduct category);

    List<Product> getAllProductsByCategory(String category);

    void saveProduct(Product product);

    int saveNewAmountOfProduct(Product product, int amount);

    String getPageName(String page);

    Set<CategoryOfProduct> getAllCategories();

    void saveCategory(CategoryOfProduct category);

    List<CategoryOfProduct> getCategoryByName(String categoryName);

    int deleteCategoryByName(String categoryName);

}
