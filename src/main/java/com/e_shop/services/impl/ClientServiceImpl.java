package com.e_shop.services.impl;

import com.e_shop.dao.ClientDao;
//import com.e_shop.dao2.GenericDao;
//import com.e_shop.dao2.IGenericDao;
import com.e_shop.domain.Client;
import com.e_shop.domain.Order;
import com.e_shop.services.ClientService;
import com.e_shop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public Client getClientByName(String name) {
        return dao.getClientByName(name);
    }

    @Override
    public Client getClientById(int id) {
        return dao.getClientById(id);
    }

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
        List<Client> collect = getAllClients()
                .stream()
                .filter(client -> client.getFirstName().equals(login))
                .collect(Collectors.toList());
        if (collect.size() == 0) return true;
        return false;
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
        return dao.getClientByName(s);
    }

    public void setDao(ClientDao dao) {
        this.dao = dao;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
