package com.eshop.dao.impl;

import com.eshop.dao.ClientDao;
import com.eshop.domain.Client;
import com.eshop.domain.ClientAddress;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Client getClientByEmail(String email) {
        String hql = "FROM Client WHERE email = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramId", email);
        Client client = (Client) query.list().get(0);
        return client;
    }

    @Override
    public List<Client> getAllClientsByEmail(String email) {
        String hql = "FROM Client WHERE email = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramId", email);
        List list = query.list();
        return list;
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
    public ClientAddress getAddressById(int id) {
        String hql = "FROM ClientAddress WHERE id = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramId", id);
        ClientAddress address = (ClientAddress) query.list().get(0);
        return address;
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

    @Override
    public void saveAddress(ClientAddress address) {
        sessionFactory.getCurrentSession().saveOrUpdate(address);
    }

    @Override
    public void deleteAddressById(int id) {
        String hql = "DELETE ClientAddress where id = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("paramId", id);
        int result = query.executeUpdate();
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
