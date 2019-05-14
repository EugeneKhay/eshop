package com.eshop.service;

import com.eshop.domain.Client;
import com.eshop.domain.ClientAddress;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.List;

public interface ClientService extends UserDetailsService {

    Client getClientById(int id);

    List<Client> getAllClientsByEmail(String email);

    List<Client> getAllClients();

    void saveClient(Client client);

    List<Client> getTenBestClientsPerPeriod(LocalDate start, LocalDate finish);

    boolean checkLogin(String login);

    boolean registerNewClient( String firstName, String lastName, LocalDate birthDate, String email, String phone, String password);

    Client editClientPersonalData(int id, String firstName, String lastName, String password, String email, String phone);

    ClientAddress getAddressById(int id);

    void saveAddress(ClientAddress address);

    Client createAddressForClient(String country, String city, int postcode, String street, int houseNumber, int flatNumber);

    Client editAddressForClient(int addressForEdit, String country, String city, int postcode, String street, int houseNumber, int flatNumber);

    void deleteAddressById(int id);

    Client getClientForView();






}
