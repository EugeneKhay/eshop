package com.eshop.dao.impl;

import com.eshop.dao.ClientDao;
import com.eshop.domain.Client;
import com.eshop.domain.ClientAddress;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Contains implementations of ClientDao methods
 */
@Repository
public class ClientDaoImpl implements ClientDao {

    private static String PARAM = "paramId";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Client getClientByName(String name) {
        String hql = "FROM Client WHERE firstname = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, name);
        return  (Client) query.list().get(0);
    }

    @Override
    public Client getClientByEmail(String email) {
        String hql = "FROM Client WHERE email = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, email);
        return  (Client) query.list().get(0);
    }

    @Override
    public List<Client> getAllClientsByEmail(String email) {
        String hql = "FROM Client WHERE email = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, email);
        return query.list();
    }

    @Override
    public Client getClientById(int id) {
        String hql = "FROM Client WHERE id = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, id);
        return (Client) query.list().get(0);
    }

    @Override
    public ClientAddress getAddressById(int id) {
        String hql = "FROM ClientAddress WHERE id = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, id);
        return  (ClientAddress) query.list().get(0);
    }

    @Override
    public List<Client> getAllClients() {
        String hql = "FROM Client";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public void saveClient(Client client) {
        sessionFactory.getCurrentSession().saveOrUpdate(client);
    }

    @Override
    public void saveAddress(ClientAddress address) {
        sessionFactory.getCurrentSession().save(address);
    }

    @Override
    public void deleteAddressById(int id) {
        String hql = "DELETE ClientAddress where id = :paramId";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(PARAM, id);
        query.executeUpdate();
    }
}