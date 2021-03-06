package com.eshop.service.impl;

import com.eshop.domain.*;
import com.eshop.jms.MessageSender;
import com.eshop.service.AdminService;
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
import java.util.logging.Logger;

/**
 * Contains the methods to:
 * ensure functions that used by admin user;
 * get data to display to the admin page
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private static final String IMAGE_PATH = "/resources/static/images/";

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MessageSender sender;

    private Logger logger = Logger.getLogger("logger");


    /**
     * Define the start date.
     * @param start input date
     * @return the start date
     */
    @Override
    public LocalDate getStartDate(LocalDate start) {
        return (start == null) ? LocalDate.of(2019, 1, 1) : start;
    }

    /**
     * Define the finish date.
     * @param finish input date
     * @return the finish date
     */
    @Override
    public LocalDate getFinishDate(LocalDate finish) {
        return (finish == null) ? LocalDate.now() : finish;
    }

    /**
     * Create the message that represents the period of time in manager page.
     * @param start start date
     * @param finish finish date
     * @return message for manager page
     */
    @Override
    public String getMessage(LocalDate start, LocalDate finish) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return "Info for the period from " + start.format(formatter)
                + " to " + finish.format(formatter);
    }

    /**
     * Set data for view with particular dates.
     * @param model model for view in MVC
     * @param start start date
     * @param finish finish date
     */
    @Override
    public void setStats(Model model, LocalDate start, LocalDate finish) {
        model.addAttribute("bestProducts", orderService.getBestsellerPerPeriod(start, finish));
        model.addAttribute("orders", orderService.getOrdersPerPeriod(start, finish));
        model.addAttribute("totalSumOfAllOrders", orderService.getTotalSumOfAllOrdersPerPeriod(start, finish));
        model.addAttribute("totalAmountOfOrders", orderService.getTotalAmountOfOrdersPerPeriod(start, finish));
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("period", getMessage(start, finish));
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("shops", orderService.getAllShops());
        List<Client> tenBest = clientService.getTenBestClientsPerPeriod(start, finish);
        List<Integer> integers = numberOfOrdersForTenBestClients(tenBest, start, finish);
        List<String> output = new ArrayList<>();
        for (int i = 0; i < tenBest.size(); i++) {
            output.add(tenBest.get(i).getFirstName() + " " + tenBest.get(i).getLastName() + ", " + integers.get(i) +  " orders");
        }
        model.addAttribute("output", output);
        logger.info("Statistics set");
    }

    /**
     * Set data for view with default dates: start - 01.01.2019 (the start date of the shop),
     * finish - the current day.
     * @param model model for view in MVC
     */
    @Override
    public void setStatsDefaultDate(Model model) {
        LocalDate start = LocalDate.of(2019, 1, 1);
        LocalDate finish = LocalDate.now();
        setStats(model, start, finish);
    }

    /**
     * Calculate the amount of orders
     * @param clientList
     * @param start
     * @param finish
     * @return
     */
    @Override
    public List<Integer> numberOfOrdersForTenBestClients(List<Client> clientList, LocalDate start, LocalDate finish) {
        List<Integer> numbers = new ArrayList<>();
        for (Client client: clientList) {
            int numberOfOrders = orderService.getOrdersPerPeriodForClient(client, start, finish).size();
            numbers.add(numberOfOrders);
        }
        return numbers;
    }

    /**
     * Adds new product to DB
     * @param productName the name of a product
     * @param productPrice the price of a product
     * @param category the cathegory of a product
     * @param amount the total amount of a product
     * @param colour the colour of a product
     * @param brand the brand of a product
     * @param weight the weight of a product
     * @param operatingSystem the OS, if a product has it
     * @param image the name of folder, which contains images of a product
     */
    @Override
    public void addNewProduct(String productName, double productPrice, String category, int amount,
                              String colour, String brand, int weight, String operatingSystem, String image) {
        ProductParameteres productParameteres = new ProductParameteres(colour, brand, weight, operatingSystem);
        CategoryOfProduct productCategory = productService.getSingleCategoryByName(category);
        String imagePath = IMAGE_PATH + image;
        Product product = new Product(productName, productPrice, amount, imagePath, productParameteres, productCategory);
        productService.saveProduct(product);
        sender.sendMessage("Update");
    }

    /**
     * Checks if the category doesn't already exists.
     * @param categoryName
     * @return true if category doesn't exists, false for vice-versa.
     */
    @Override
    public boolean checkCategory(String categoryName) {
        List<CategoryOfProduct> listOfCategoryByName = productService.getCategoryByName(categoryName);
            return listOfCategoryByName.isEmpty();
    }

    @Override
    public ShopAddress createShopAddress(String country, String city, int postcode, String street, int houseNumber, String phone) {
        if (country == null || city == null || postcode == 0 ||street == null || houseNumber == 0 || phone == null) throw new IllegalArgumentException("Not valid data about shop address");
        ShopAddress shopAddress = new ShopAddress(country, city, postcode, street, houseNumber, phone);
        orderService.saveShop(shopAddress);
        return shopAddress;
    }

}