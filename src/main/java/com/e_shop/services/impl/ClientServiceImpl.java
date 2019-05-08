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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;
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

    private Logger logger = Logger.getLogger("logger");


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
        List<Client> clientByEmail = getAllClientsByEmail(login);
        if (clientByEmail.size() == 0) return true;
        return false;
    }

    @Override
    public boolean registerNewClient(String firstName, String lastName, LocalDate birthDate, String email, String password) {
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setBirthDate(birthDate);
        client.setEmail(email);
        client.setPassword(passwordEncoder.encode(password));
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
        Client client = getClientById(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPassword(passwordEncoder.encode(password));
        client.setEmail(email);
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
    public Client createAddressForClient(String country, String city, int postcode, String street, int houseNumber, int flatNumber) {
        ClientAddress address = new ClientAddress(country, city, postcode, street, houseNumber, flatNumber);
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ClientAddress> addresses = client.getAddressList();
        addresses.add(address);
        saveAddress(address);
        client.setAddressList(addresses);
        saveClient(client);
        return client;
    }

    @Override
    public Client editAddressForClient(int addressForEdit, String country, String city, int postcode, String street, int houseNumber, int flatNumber) {
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientAddress newAddress = getAddressById(addressForEdit);
        logger.info("Old address " + newAddress + " retrieved");
        newAddress.setCountry(country);
        newAddress.setCity(city);
        newAddress.setPostCode(postcode);
        newAddress.setStreet(street);
        newAddress.setHouseNumber(houseNumber);
        newAddress.setFlatNumber(flatNumber);
        saveAddress(newAddress);
        logger.info("New address " + newAddress + " saved");
        List<ClientAddress> addresses = client.getAddressList();
        addresses.remove(getAddressById(addressForEdit));
        addresses.add(newAddress);
        client.setAddressList(addresses);
        return client;
    }

    @Override
    public void deleteAddressById(int id) {
        dao.deleteAddressById(id);
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
