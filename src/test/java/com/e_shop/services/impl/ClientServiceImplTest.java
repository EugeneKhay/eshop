package com.e_shop.services.impl;

import com.e_shop.dao.ClientDao;
import com.e_shop.dao.impl.ClientDaoImpl;
import com.e_shop.domain.Client;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ClientServiceImplTest {

    ClientDao daoMock = mock(ClientDaoImpl.class);
    Client client1 = mock(Client.class);
    ClientServiceImpl service = new ClientServiceImpl();


    @Before
    public void prepare() {
        service.setDao(daoMock);
        when(client1.getFirstName()).thenReturn("John");
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        when(service.getAllClients()).thenReturn(clients);
    }

    @Test
    public void checkLoginWithExistingLogin() {
        assertEquals(service.checkLogin("John"), false);
    }

    @Test
    public void checkLoginWithNewLogin() {
        assertEquals(service.checkLogin("Bob"), true);
    }
}