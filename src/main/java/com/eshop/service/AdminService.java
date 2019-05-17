package com.eshop.service;

import com.eshop.domain.*;
import org.springframework.ui.Model;
import java.time.LocalDate;
import java.util.List;

public interface AdminService {

    LocalDate getStartDate(LocalDate start);

    LocalDate getFinishDate(LocalDate finish);

    String getMessage(LocalDate start, LocalDate finish);

    void setStats(Model model, LocalDate start, LocalDate finish);

    void setStatsDefaultDate(Model model);

    List<Integer> numberOfOrdersForTenBestClients(List<Client> clientList, LocalDate start, LocalDate finish);

    void addNewProduct(String productName, double productPrice, String category, int amount,
                              String colour, String brand, int weight, String operatingSystem, String image);

    boolean checkCategory(String categoryName);

    ShopAddress createShopAddress(String country, String city, int postcode, String street, int houseNumber, String phone);

}
