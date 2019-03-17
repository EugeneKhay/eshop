package com.e_shop.services.impl;

import com.e_shop.dao.ClientDao;
import com.e_shop.dao2.GenericDao;
import com.e_shop.dao2.IGenericDao;
import com.e_shop.domain.Client;
import com.e_shop.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao dao;

    @Override
    public Client getClientByName(String name) {
        return dao.getClientByName(name);
    }

    @Override
    public Client getClientById(int id) {
        return dao.getClientById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return dao.getAllClients();
    }

    @Override
    public void saveClient(Client client) {
        dao.saveClient(client);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dao.getClientByName(s);
    }
}
