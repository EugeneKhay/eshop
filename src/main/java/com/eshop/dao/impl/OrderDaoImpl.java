package com.eshop.dao.impl;

import com.eshop.dao.OrderDao;
import com.eshop.domain.Client;
import com.eshop.domain.ClientAddress;
import com.eshop.domain.Order;
import com.eshop.domain.ShopAddress;
import com.eshop.enums.OrderStatus;
import com.eshop.enums.PaymentStatus;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        //sessionFactory.getCurrentSession().save(order);
        sessionFactory.getCurrentSession().saveOrUpdate(order);
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

    @Override
    public void saveShop(ShopAddress address) {
        sessionFactory.getCurrentSession().saveOrUpdate(address);
    }

    //TODO remove set
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
        query.setParameter("param", id);
        ShopAddress address = (ShopAddress) query.list().get(0);
        return address;
    }

    @Override
    public ClientAddress getClientAddressById(Integer id) {
        String hql = "FROM ClientAddress WHERE id = :param";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("param", id);
        ClientAddress address = (ClientAddress) query.list().get(0);
        return address;
    }
}

