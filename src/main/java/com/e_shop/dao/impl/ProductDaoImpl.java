package com.e_shop.dao.impl;

import com.e_shop.dao.ProductDAO;
import com.e_shop.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
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

    @Override
    public List<Product> getAllProducts() {
        String hql = "FROM Product";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<Product> getAllProductsByPrice(double priceMin, double priceMax) {
        String hql = "FROM Product WHERE productprice between :minprice and :maxprice";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("maxprice", priceMax);
        query.setParameter("minprice", priceMin);
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
    public void saveProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }
}
