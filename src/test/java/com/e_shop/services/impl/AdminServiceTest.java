package com.e_shop.services.impl;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AdminServiceTest {

    private AdminService adminService = new AdminService();

    @Test
    public void getStartDate() {
        LocalDate expected = LocalDate.of(2019, 3, 8);
        assertEquals(expected, adminService.getStartDate(LocalDate.of(2019, 3, 8)));
        assertEquals(LocalDate.of(2019, 1, 1), adminService.getStartDate(null));
    }

    @Test
    public void getFinishDateTest() {
        LocalDate expected = LocalDate.now();
        LocalDate expected2 = LocalDate.of(2019, 2, 23);
        assertEquals(expected, adminService.getFinishDate(null));
        assertEquals(expected2, adminService.getFinishDate(LocalDate.of(2019, 2, 23)));
    }

    @Test
    public void getMessage() {
        LocalDate start = LocalDate.of(2019, 2, 23);
        LocalDate finish = LocalDate.of(2019, 3, 8);
        String expected = "Info for the period from 23.02.2019 to 08.03.2019";
        assertEquals(expected, adminService.getMessage(start, finish));
    }
}