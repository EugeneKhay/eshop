package com.eshop.dao.impl;

import com.eshop.dao.OrderDao;
import com.eshop.domain.Client;
import com.eshop.domain.Order;
import com.eshop.enums.OrderStatus;
import com.eshop.enums.PaymentStatus;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Order getOrderById(Integer id) {
        String hql = "FROM Order WHERE id = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", id);
        Order order = (Order) query.list().get(0);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        String hql = "FROM Order";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public void saveOrders(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public int updateOrder(int orderId, String payStatus, String ordStatus) {
        String hql = "update Order set paymentStatus = :payParam, orderStatus = :ordParam where id = :idParam";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("idParam", orderId);
        query.setParameter("payParam", PaymentStatus.valueOf(payStatus));
        query.setParameter("ordParam", OrderStatus.valueOf(ordStatus));
        int updateResult = query.executeUpdate();
        return updateResult;
    }

    @Override
    public List<Order> getOrdersPerPeriod(LocalDate start, LocalDate finish) {
        String hql = "FROM Order WHERE dateOfOrder between :start and :finish";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("start", start);
        query.setParameter("finish", finish);
        return query.list();
    }

    @Override
    public List<Order> getOrdersPerPeriodForClient(Client client, LocalDate start, LocalDate finish) {
        String hql = "FROM Order as o WHERE o.client = :paramClient and o.dateOfOrder between :start and :finish";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramClient", client);
        query.setParameter("start", start);
        query.setParameter("finish", finish);
        return query.list();
    }
}
