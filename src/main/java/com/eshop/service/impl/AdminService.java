package com.eshop.service.impl;

import com.eshop.domain.CategoryOfProduct;
import com.eshop.domain.Client;
import com.eshop.domain.Product;
import com.eshop.domain.ProductParameteres;
import com.eshop.enums.ProductCategory;
import com.eshop.service.ClientService;
import com.eshop.service.OrderService;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminService {

    private final static String IMAGE_PATH = "../resources/static/images/";

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    public LocalDate getStartDate(LocalDate start) {
        return (start == null) ? LocalDate.of(2019, 1, 1) : start;
    }

    public LocalDate getFinishDate(LocalDate finish) {
        return (finish == null) ? LocalDate.now() : finish;
    }

    public String getMessage(LocalDate start, LocalDate finish) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String period = "Info for the period from " + start.format(formatter)
                + " to " + finish.format(formatter);
        return period;
    }

    public void setStats(Model model, LocalDate start, LocalDate finish) {
        model.addAttribute("bestProducts", orderService.getBestsellerPerPeriod(start, finish));
        model.addAttribute("orders", orderService.getOrdersPerPeriod(start, finish));
        model.addAttribute("totalSumOfAllOrders", orderService.getTotalSumOfAllOrdersPerPeriod(start, finish));
        model.addAttribute("totalAmountOfOrders", orderService.getTotalAmountOfOrdersPerPeriod(start, finish));
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("period", getMessage(start, finish));
        //EXP
        model.addAttribute("categories", productService.getAllCategories());
        List<Client> tenBest = clientService.getTenBestClientsPerPeriod(start, finish);
        List<Integer> integers = numberOfOrdersForTenBestClients(tenBest, start, finish);
        List<String> output = new ArrayList<>();
        for (int i = 0; i < tenBest.size(); i++) {
            output.add(tenBest.get(i).getFirstName() + " " + tenBest.get(i).getLastName() + ", " + integers.get(i) +  " orders");
        }
        model.addAttribute("output", output);
    }

    public List<Integer> numberOfOrdersForTenBestClients(List<Client> clientList, LocalDate start, LocalDate finish) {
        List<Integer> numbers = new ArrayList<>();
        for (Client client: clientList) {
            int numberOfOrders = orderService.getOrdersPerPeriodForClient(client, start, finish).size();
            numbers.add(numberOfOrders);
        }
        return numbers;
    }

    public void addNewProduct(String productName, double productPrice, String category, int amount,
                              String colour, String brand, String image) {
        ProductParameteres productParameteres = new ProductParameteres(colour, brand);

//        CategoryOfProduct productCategory;
//        if (checkCategory(category)) {
//            productCategory = new CategoryOfProduct(category);
//        } else {
//            productCategory = productService.getCategoryByName(category).get(0);
//        }

        CategoryOfProduct productCategory = new CategoryOfProduct(category);
        String imagePath = IMAGE_PATH + image;
        Product product = new Product(productName, productPrice, amount, imagePath, productParameteres, productCategory);
        productService.saveProduct(product);
    }

    public boolean checkCategory(String categoryName) {
        List<CategoryOfProduct> listOfCategoryByName = productService.getCategoryByName(categoryName);
        if (listOfCategoryByName.size() == 0) return true;
        return false;
    }

}
