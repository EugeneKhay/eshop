package com.e_shop.dao.impl;

//import com.e_shop.dao.HibernateUtils;
import com.e_shop.dao.ProductDAO;
import com.e_shop.domain.Product;
import com.e_shop.enums.ProductCategory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<Product> getAllProductsByPrice(double priceMin, double priceMax, String type) {
        ProductCategory category = ProductCategory.valueOf(type.toUpperCase());
        String hql = "FROM Product as p WHERE p.category = :paramCategory and p.productPrice between :minprice and :maxprice";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("maxprice", priceMax);
        query.setParameter("minprice", priceMin);
        query.setParameter("paramCategory", category);
        return query.list();
    }

    //search by category and brand
    @Override
    public List<Product> getAllProductsByBrand(String brand, String type) {
        ProductCategory category = ProductCategory.valueOf(type.toUpperCase());
        String hql = "FROM Product as p WHERE p.category = :paramCategory and p.productParameteres.brand = :paramBrand" ;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramCategory", category);
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
        ProductCategory category = ProductCategory.valueOf(type.toUpperCase());
        String hql = "FROM Product as p WHERE p.category = :paramCategory and p.productParameteres.colour = :paramColour" ;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramCategory", category);
        query.setParameter("paramColour", colour);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByCategory(ProductCategory category) {
        String hql = "FROM Product as p WHERE p.category = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", category);
        return query.list();
    }

    @Override
    public void saveProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public int saveNewAmountOfProduct(Product product, int amount) {
        String hql = "update com.e_shop.domain.Product set amount = :amountParam where id = :idParam";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("amountParam", amount);
        query.setParameter("idParam", product.getId());
        int updateResult = query.executeUpdate();
        return updateResult;
    }


}
