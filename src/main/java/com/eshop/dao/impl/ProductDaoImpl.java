package com.eshop.dao.impl;

//import com.eshop.dao.HibernateUtils;
import com.eshop.dao.ProductDAO;
import com.eshop.domain.CategoryOfProduct;
import com.eshop.domain.Product;
import com.eshop.enums.ProductCategory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ProductDaoImpl implements ProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Product getProductByName(String name) {
        String hql = "FROM Product WHERE productname = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", name);
        Product product = (Product) query.list().get(0);
        return product;
    }

//    @Override
//    public Product getProductByName(String name) {
//        String hql = "FROM Product WHERE productname = :param";
//        Query query = HibernateUtils.getQuery(hql);
//        //Query query = sessionFactory.getCurrentSession().createQuery(hql);
//        query.setParameter("param", name);
//        //Product product = (Product) query.list().get(0);
//        Product product = (Product) query.uniqueResult();
//        return product;
//    }

    @Override
    public Product getProductById(int id) {
        String hql = "FROM Product WHERE id = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", id);
        Product product = (Product) query.list().get(0);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        String hql = "FROM Product";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByPrice(double priceMin, double priceMax) {
        String hql = "FROM Product WHERE productPrice between :minprice and :maxprice";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("maxprice", priceMax);
        query.setParameter("minprice", priceMin);
        return query.list();
    }

//    @Override
//    public List<Product> getAllProductsByPrice(double priceMin, double priceMax, String type) {
//        ProductCategory category = ProductCategory.valueOf(type.toUpperCase());
//        String hql = "FROM Product as p WHERE p.category = :paramCategory and p.productPrice between :minprice and :maxprice";
//        Query query = sessionFactory.getCurrentSession().createQuery(hql);
//        query.setParameter("maxprice", priceMax);
//        query.setParameter("minprice", priceMin);
//        query.setParameter("paramCategory", category);
//        return query.list();
//    }

    @Override
    public List<Product> getAllProductsByPrice(double priceMin, double priceMax, String type) {
        //ProductCategory category = ProductCategory.valueOf(type.toUpperCase());
        String hql = "FROM Product as p WHERE p.productCategory.categoryName = :paramCategory and p.productPrice between :minprice and :maxprice";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("maxprice", priceMax);
        query.setParameter("minprice", priceMin);
        query.setParameter("paramCategory", type);
        return query.list();
    }

    //search by category and brand
    @Override
    public List<Product> getAllProductsByBrand(String brand, String type) {
        //ProductCategory category = ProductCategory.valueOf(type.toUpperCase());
        String hql = "FROM Product as p WHERE p.productCategory.categoryName = :paramCategory and p.productParameteres.brand = :paramBrand" ;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramCategory", type);
        query.setParameter("paramBrand", brand);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByBrand(String brand) {
        String hql = "FROM Product as p WHERE p.productParameteres.brand = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", brand);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByColour(String colour) {
        String hql = "FROM Product as p WHERE p.productParameteres.colour = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", colour);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByColour(String colour, String type) {
        //ProductCategory category = ProductCategory.valueOf(type.toUpperCase());
        String hql = "FROM Product as p WHERE p.productCategory.categoryName = :paramCategory and p.productParameteres.colour = :paramColour" ;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramCategory", type);
        query.setParameter("paramColour", colour);
        return query.list();
    }

//    @Override
//    public List<Product> getAllProductsByCategory(ProductCategory category) {
//        String hql = "FROM Product as p WHERE p.category = :param";
//        Query query = sessionFactory.getCurrentSession().createQuery(hql);
//        query.setParameter("param", category);
//        return query.list();
//    }

    @Override
    public List<Product> getAllProductsByCategory(CategoryOfProduct category) {
        String hql = "FROM Product as p WHERE p.productCategory = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", category);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        String hql = "FROM Product as p WHERE p.productCategory.categoryName = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", category);
        return query.list();
    }

    @Override
    public void saveProduct(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
    }

    @Override
    public int saveNewAmountOfProduct(Product product, int amount) {
        String hql = "update com.eshop.domain.Product set amount = :amountParam where id = :idParam";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("amountParam", amount);
        query.setParameter("idParam", product.getId());
        int updateResult = query.executeUpdate();
        return updateResult;
    }

    @Override
    public String getPageName(String page) {
        // delete symbol "/"
        String page2 = page.substring(1);
        String pageName = "Products";
        if (page2.equals("phone")) pageName = "Phones";
        if (page2.equals("tv")) pageName = "TV & Video";
        if (page2.equals("audio")) pageName = "Audio & Hi-Fi";
        if (page2.equals("laptop")) pageName = "Laptops";
        if (page2.equals("tablet")) pageName = "Tablets";
        return pageName;
    }

    @Override
    public Set<CategoryOfProduct> getAllCategories() {
        String hql = "FROM CategoryOfProduct";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return new HashSet<>(query.list());
    }

//    @Override
//    public void saveCategory(CategoryOfProduct category) {
//        sessionFactory.getCurrentSession().save(category);
//    }

    @Override
    public void saveCategory(CategoryOfProduct category) {
        sessionFactory.getCurrentSession().saveOrUpdate(category);
    }

    @Override
    public List<CategoryOfProduct> getCategoryByName(String categoryName) {
        String hql = "FROM CategoryOfProduct WHERE categoryName = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", categoryName);
        List<CategoryOfProduct> category = query.list();
        return category;
    }


}