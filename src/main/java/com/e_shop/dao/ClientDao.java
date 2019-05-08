package com.e_shop.dao;

import com.e_shop.domain.Client;
import com.e_shop.domain.ClientAddress;

import java.time.LocalDate;
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

    //List<Client> getAllClientsPerPeriod(LocalDate start, LocalDate finish);


}
