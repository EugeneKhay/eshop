package com.eshop.dao.impl;

import com.eshop.dao.ProductDAO;
import com.eshop.domain.CategoryOfProduct;
import com.eshop.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ProductDaoImpl implements ProductDAO {

    private static String PARAM = "param";
    private static String PARAM_CATEGORY = "paramCategory";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Product getProductByName(String name) {
        String hql = "FROM Product WHERE productname = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, name);
        return  (Product) query.list().get(0);
    }

    @Override
    public Product getProductById(int id) {
        String hql = "FROM Product WHERE id = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, id);
        return  (Product) query.list().get(0);
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

    @Override
    public List<Product> getAllProductsByPrice(double priceMin, double priceMax, String type) {
        String hql = "FROM Product as p WHERE p.productCategory.categoryName = :paramCategory and p.productPrice between :minprice and :maxprice";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("maxprice", priceMax);
        query.setParameter("minprice", priceMin);
        query.setParameter(PARAM_CATEGORY, type);
        return query.list();
    }

    //search by category and brand
    @Override
    public List<Product> getAllProductsByBrand(String brand, String type) {
        String hql = "FROM Product as p WHERE p.productCategory.categoryName = :paramCategory and p.productParameteres.brand = :paramBrand" ;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM_CATEGORY, type);
        query.setParameter("paramBrand", brand);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByBrand(String brand) {
        String hql = "FROM Product as p WHERE p.productParameteres.brand = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, brand);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByColour(String colour) {
        String hql = "FROM Product as p WHERE p.productParameteres.colour = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, colour);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByColour(String colour, String type) {
        String hql = "FROM Product as p WHERE p.productCategory.categoryName = :paramCategory and p.productParameteres.colour = :paramColour" ;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM_CATEGORY, type);
        query.setParameter("paramColour", colour);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByCategory(CategoryOfProduct category) {
        String hql = "FROM Product as p WHERE p.productCategory = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, category);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        String hql = "FROM Product as p WHERE p.productCategory.categoryName = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, category);
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
    public Set<CategoryOfProduct> getAllCategories() {
        String hql = "FROM CategoryOfProduct";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return new HashSet<>(query.list());
    }

    @Override
    public CategoryOfProduct getSingleCategoryByName(String name) {
        String hql = "FROM CategoryOfProduct WHERE categoryName = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, name);
        return  (CategoryOfProduct) query.list().get(0);
    }

    @Override
    public void saveCategory(CategoryOfProduct category) {
        sessionFactory.getCurrentSession().saveOrUpdate(category);
    }

    @Override
    public List<CategoryOfProduct> getCategoryByName(String categoryName) {
        String hql = "FROM CategoryOfProduct WHERE categoryName = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, categoryName);
        return query.list();
    }

    @Override
    public int deleteCategoryByName(String categoryName) {
        String hql = "DELETE FROM CategoryOfProduct AS p where p.categoryName = :paramName";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramName", categoryName);
        return query.executeUpdate();
    }
}
