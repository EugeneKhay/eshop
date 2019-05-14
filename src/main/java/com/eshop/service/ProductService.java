package com.eshop.service;

import com.eshop.domain.CategoryOfProduct;
import com.eshop.domain.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {

    Product getProductByName(String name);

    Product getProductById(int id);

    List<Product> getAllProducts();

    List<Product> getAllProductsByPrice(double priceMin, double priceMax);

    List<Product> getAllProductsByPrice(double priceMin, double priceMax, String type);

    List<Product> getAllProductsByBrand(String brand);

    void saveProduct(Product product);

    int decreaseProductAmountInStock(Product product, int countOfItems);

    int saveNewAmountOfProduct(Product product, int amount);

    List<Product> getAllProductsByCategory(CategoryOfProduct category);

    List<Product> getAllProductsByCategory(String category);

    List<Product> getAllProductsByBrand(String brand, String type);

    List<Product> getAllProductsByColour(String colour, String type);

    void editProductByAdmin(int productId, String productName, String brand, double price, int amount,
                            String category, String colour, int weight, String operatingSystem);

    Set<CategoryOfProduct> getAllCategories();

    void saveCategory(CategoryOfProduct category);

    List<CategoryOfProduct> getCategoryByName(String categoryName);

    int deleteCategoryByName(String categoryName);

    CategoryOfProduct getSingleCategoryByName(String name);


}
