package com.eshop.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateUtils {

    @Autowired
    static SessionFactory sessionFactory;

    public static Query getQuery(String hql) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query;
    }
}
