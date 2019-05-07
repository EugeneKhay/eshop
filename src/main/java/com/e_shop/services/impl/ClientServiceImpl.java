package com.e_shop.services.impl;

import com.e_shop.dao.ClientDao;
//import com.e_shop.dao2.GenericDao;
//import com.e_shop.dao2.IGenericDao;
import com.e_shop.domain.Client;
import com.e_shop.domain.ClientAddress;
import com.e_shop.domain.Order;
import com.e_shop.enums.Role;
import com.e_shop.exception.LoginException;
import com.e_shop.services.ClientService;
import com.e_shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao dao;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Client getClientByName(String name) {
        return dao.getClientByName(name);
    }

    @Override
    public Client getClientById(int id) {
        return dao.getClientById(id);
    }

    @Override
    public Client getClientByEmail(String email) {
        return dao.getClientByEmail(email);
    }

    @Override
    public List<Client> getAllClientsByEmail(String email) {
        return dao.getAllClientsByEmail(email);
    }

//    @Override
//    public List<Client> getClientByEmail2(String email) {
//        return dao.getAllClientsByEmail(email);
//    }

    @Override
    public List<Client> getAllClients() {
        return dao.getAllClients();
    }


    @Override
    public void saveClient(Client client) {
        dao.saveClient(client);
    }

    @Override
    public List<Client> getAllClientsPerPeriod(LocalDate start, LocalDate finish) {
        return null;
    }

    @Override
    public boolean checkLogin(String login) {
//        List<Client> collect = getAllClients()
//                .stream()
//                .filter(client -> client.getEmail().equals(login))
//                .collect(Collectors.toList());
//        if (collect.size() == 0) return true;
//        return false;
        List<Client> clientByEmail = getAllClientsByEmail(login);
        if (clientByEmail.size() == 0) return true;
        return false;
    }

    @Override
    public boolean registerNewClient(String firstName, String lastName, LocalDate birthDate, String email, String password) {
                                     //String country, String city, int postcode, String street, int house, int flat) {

//        ClientAddress address = new ClientAddress(country, city, postcode, street, house, flat);
//        List<ClientAddress> clientAddresses = new ArrayList<>();
//        clientAddresses.add(address);

        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setBirthDate(birthDate);
        client.setEmail(email);
        client.setPassword(passwordEncoder.encode(password));
//        client.setAddress(address);
        //client.setAddressList(clientAddresses);
        client.setRoles(Collections.singleton(Role.ROLE_USER));
        if (checkLogin(email)) {
            saveClient(client);
            return true;
        } else {
            throw new LoginException();
        }
    }

    @Override
    public Client editClientPersonalData(int id, String firstName, String lastName, String password, String email) {
                                         //String country, String city, int postcode, String street, int houseNumber, int flatNumber) {
        Client client = getClientById(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPassword(passwordEncoder.encode(password));
        client.setEmail(email);

//        ClientAddress newAddress = new ClientAddress(country, city, postcode, street, houseNumber, flatNumber);
//        List<ClientAddress> clientAddresses = new ArrayList<>();
//        clientAddresses.add(newAddress);
////        client.setAddress(newAddress);
//        client.setAddressList(clientAddresses);

        saveClient(client);
        return client;
    }

    @Override
    public ClientAddress getAddressById(int id) {
        return dao.getAddressById(id);
    }

    @Override
    public void saveAddress(ClientAddress address) {
        dao.saveAddress(address);
    }

    @Override
    public List<Client> getTenBestClientsPerPeriod(LocalDate start, LocalDate finish)
    {
        Map<Client, Long> ordersOfClientMap = new HashMap<>();
        List<Order> ordersPerPeriod = orderService.getOrdersPerPeriod(start, finish);
        List<Client> allClients = getAllClients();

        for (Client client: allClients) {
            long countOfOrderForClient = ordersPerPeriod.stream()
                                                        .filter(order -> order.getClient().equals(client))
                                                        .count();
            ordersOfClientMap.put(client, countOfOrderForClient);
        }
        List<Long> amountOfOrders = ordersOfClientMap
                                .values()
                                .stream()
                                .sorted(Comparator.reverseOrder())
                                .collect(Collectors.toList());
        Set<Client> bestTenClients = new LinkedHashSet<>();

        amountOfOrders.forEach(i -> {
            for (Map.Entry<Client, Long> entry : ordersOfClientMap.entrySet()) {
                if (i == entry.getValue())
                    bestTenClients.add(entry.getKey());
            }
        });
        return bestTenClients.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dao.getClientByEmail(s);
    }

    public void setDao(ClientDao dao) {
        this.dao = dao;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
