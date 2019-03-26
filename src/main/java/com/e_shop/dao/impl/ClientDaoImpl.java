package com.e_shop.dao.impl;

import com.e_shop.dao.ClientDao;
import com.e_shop.dao2.AbstractDao;
import com.e_shop.domain.Client;
import com.e_shop.domain.ClientAddress;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Client getClientByName(String name) {
        String hql = "FROM Client WHERE firstname = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramId", name);
        Client client = (Client) query.list().get(0);
        return client;
    }

    @Override
    public Client getClientById(int id) {
        String hql = "FROM Client WHERE id = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramId", id);
        Client client = (Client) query.list().get(0);
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        String hql = "FROM Client";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        List<Client> list = query.list();
        return list;
    }

    @Override
    public void saveClient(Client client) {
        sessionFactory.getCurrentSession().saveOrUpdate(client);
    }

//    @Override
//    public List<Client> getAllClientsPerPeriod(LocalDate start, LocalDate finish) {
//        String hql = "FROM Client AS c WHERE c.orders between :start and :finish";
//        Query query = sessionFactory.getCurrentSession().createQuery(hql);
//        query.setParameter("start", start);
//        query.setParameter("finish", finish);
//        return query.list();
//    }
}
