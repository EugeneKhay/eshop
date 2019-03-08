package com.e_shop.services.impl;

import com.e_shop.dao.ClientDao;
import com.e_shop.domain.Client;
import com.e_shop.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao dao;

    @Override
    public Client getClientByName(String name) {
        return dao.getClientByName(name);
    }

    @Override
    public List<Client> getAllClients() {
        return dao.getAllClients();
    }

    @Override
    public void saveClient(Client client) {
        dao.saveClient(client);
    }
}
