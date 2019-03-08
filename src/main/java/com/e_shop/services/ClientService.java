package com.e_shop.services;

import com.e_shop.domain.Client;
import com.e_shop.domain.ClientAddress;

import java.util.List;

public interface ClientService {

    Client getClientByName(String name);

    List<Client> getAllClients();

    void saveClient(Client client);

}
