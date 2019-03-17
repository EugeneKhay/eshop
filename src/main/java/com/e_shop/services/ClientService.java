package com.e_shop.services;

import com.e_shop.domain.Client;
import com.e_shop.domain.ClientAddress;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClientService extends UserDetailsService {

    Client getClientByName(String name);

    Client getClientById(int id);

    List<Client> getAllClients();

    void saveClient(Client client);

}
