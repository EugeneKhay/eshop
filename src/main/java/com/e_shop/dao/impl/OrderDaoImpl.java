package com.e_shop.dao.impl;

import com.e_shop.dao.OrderDao;
import com.e_shop.domain.Order;
import com.e_shop.domain.Product;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order getOrderById(Integer id) {
        String hql = "FROM com.e_shop.domain.Order WHERE id = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", id);
        Order order = (Order) query.list().get(0);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        String hql = "FROM com.e_shop.domain.Order";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public void saveOrders(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }
}
