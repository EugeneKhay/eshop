package com.e_shop.dao;

import com.e_shop.domain.Client;
import com.e_shop.domain.ClientAddress;

import java.util.List;

public interface ClientDao {

    Client getClientByName(String name);

    List<Client> getAllClients();

    void saveClient(Client client);

}
