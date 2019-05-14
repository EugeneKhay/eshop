package com.eshop.dao;

import com.eshop.domain.Client;
import com.eshop.domain.ClientAddress;

import java.util.List;

public interface ClientDao {

    Client getClientByName(String name);

    Client getClientByEmail(String name);

    List<Client> getAllClientsByEmail(String name);

    Client getClientById(int id);

    ClientAddress getAddressById(int id);

    List<Client> getAllClients();

    void saveClient(Client client);

    void saveAddress(ClientAddress address);

    void deleteAddressById(int id);
}