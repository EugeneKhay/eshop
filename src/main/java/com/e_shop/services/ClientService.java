package com.e_shop.services;

import com.e_shop.domain.Client;
import com.e_shop.domain.ClientAddress;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ClientService extends UserDetailsService {

    Client getClientByName(String name);

    Client getClientById(int id);

    List<Client> getAllClients();

    void saveClient(Client client);

    List<Client> getTenBestClientsPerPeriod(LocalDate start, LocalDate finish);

    List<Client> getAllClientsPerPeriod(LocalDate start, LocalDate finish);

    boolean checkLogin(String login);




}
