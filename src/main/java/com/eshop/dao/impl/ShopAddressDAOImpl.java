package com.eshop.dao.impl;

import com.eshop.dao.ShopAddressDAO;
import com.eshop.domain.ShopAddress;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ShopAddressDAOImpl implements ShopAddressDAO {

    private static String PARAM = "param";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveShop(ShopAddress address) {
        sessionFactory.getCurrentSession().saveOrUpdate(address);
    }

    @Override
    public Set<ShopAddress> getAllShops() {
        String hql = "FROM ShopAddress";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return new HashSet<>(query.list());
    }

    @Override
    public ShopAddress getShopById(Integer id) {
        String hql = "FROM ShopAddress WHERE id = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, id);
        ShopAddress address = (ShopAddress) query.list().get(0);
        return address;
    }
}
