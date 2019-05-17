package com.eshop.service.impl;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AdminServiceTest {

    private AdminServiceImpl adminService = new AdminServiceImpl();

    @Test
    public void getStartDate() {
        LocalDate expected = LocalDate.of(2019, 3, 8);
        assertEquals(expected, adminService.getStartDate(LocalDate.of(2019, 3, 8)));
    }

    @Test
    public void getStartDateNull() {
        assertEquals(LocalDate.of(2019, 1, 1), adminService.getStartDate(null));
    }

    @Test
    public void getFinishDateTestNull() {
        LocalDate expected = LocalDate.now();
        assertEquals(expected, adminService.getFinishDate(null));
    }

    @Test
    public void getFinishDateTest() {
        LocalDate expected2 = LocalDate.of(2019, 2, 23);
        assertEquals(expected2, adminService.getFinishDate(LocalDate.of(2019, 2, 23)));
    }

    @Test
    public void getMessageTest() {
        LocalDate start = LocalDate.of(2019, 2, 23);
        LocalDate finish = LocalDate.of(2019, 3, 8);
        String expected = "Info for the period from 23.02.2019 to 08.03.2019";
        assertEquals(expected, adminService.getMessage(start, finish));
    }
}