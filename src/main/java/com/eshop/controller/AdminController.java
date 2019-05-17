package com.eshop.controller;

import com.eshop.domain.CategoryOfProduct;
import com.eshop.domain.ShopAddress;
import com.eshop.service.AdminService;
import com.eshop.service.OrderService;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * Contains methods for requests to the manager page processing
 */
@Controller
public class AdminController {

    private Logger logger = Logger.getLogger("logger");

    private static String PAGE_NAME = "adminpage";

    @Autowired
    private ProductService productService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderService orderService;

    /**
     * Handle the request to "/admin" URL to go to the manager page
     * @param model model for view displaying
     * @return name of the corresponding view
     */
    @GetMapping("/admin")
    public String viewAdminPage(Model model) {
        logger.info("Go to adminpage");
        adminService.setStatsDefaultDate(model);
        return PAGE_NAME;
    }

    /**
     * Handle the request to "/addproducts" URL to add new product to catalog by manager
     * @param productName name of the new product
     * @param productPrice price of the new product
     * @param category category of the new product
     * @param amount amount of the new product
     * @param colour colour of the new product
     * @param brand brand of the new product
     * @param weight weight of the new product
     * @param operatingSystem OS of the new product
     * @param image name of the folder that contains images of new product
     * @param model model for view
     * @return name of the corresponding view
     */
    @PostMapping("/addproducts")
    public String addProduct(@RequestParam(name = "productName") String productName,
                             @RequestParam(name = "productPrice") double productPrice,
                             @RequestParam(name = "category") String category,
                             @RequestParam(name = "amount") int amount,
                             @RequestParam(name = "colour") String colour,
                             @RequestParam(name = "brand") String brand,
                             @RequestParam(name = "weight") int weight,
                             @RequestParam(name = "os", required = false) String operatingSystem,
                             @RequestParam(name = "image", required = false) String image,
                             Model model) {
        adminService.addNewProduct(productName, productPrice, category, amount,
                                   colour, brand, weight, operatingSystem, image);
        logger.info("Product added");
        adminService.setStatsDefaultDate(model);
        return PAGE_NAME;
    }

    /**
     * Handle the request to "/addnewcategory" URL to add new category
     * @param model model for view displaying
     * @return name of the corresponding view
     */
    @PostMapping("/addnewcategory")
    public String addCategory(@RequestParam(name = "categoryName") String categoryName, Model model) {
            if (adminService.checkCategory(categoryName)) {
                CategoryOfProduct category = new CategoryOfProduct(categoryName);
                productService.saveCategory(category);
                logger.info("Category added");
            }
        adminService.setStatsDefaultDate(model);
        return PAGE_NAME;
    }

    /**
     * Handle the request to "/addnewcategory" URL to delete existing category
     * @param model model for view displaying
     * @return name of the corresponding view
     */
    @PostMapping("/deletecategory")
    public String deleteCategory(@RequestParam(name = "categoryForRemove") String categoryName, Model model) {
        productService.deleteCategoryByName(categoryName);
        adminService.setStatsDefaultDate(model);
        return PAGE_NAME;
    }

    /**
     * Handle the request to "/admin" URL to get data for the particular period of time
     * @param model model for view displaying
     * @return name of the corresponding view
     */
    @PostMapping("/admin")
    public String getStats(@RequestParam(name = "start", required = false) @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate startParameter,
                           @RequestParam(name = "finish", required = false)@DateTimeFormat(pattern="yyyy-MM-dd")LocalDate finishParameter,
                           Model model) {
        LocalDate start = adminService.getStartDate(startParameter);
        LocalDate finish = adminService.getFinishDate(finishParameter);
        adminService.setStats(model, start, finish);
        logger.info("Stats setted");
        return PAGE_NAME;
    }

    /**
     * Handle the request to "/addshop" URL to add new shop address
     * @param model model for view displaying
     * @return name of the corresponding view
     */
    @PostMapping("/addshop")
    public String addShopAddress (@RequestParam(name = "country") String country,
                                  @RequestParam(name = "city") String city,
                                  @RequestParam(name = "postcode") int postcode,
                                  @RequestParam(name = "street") String street,
                                  @RequestParam(name = "houseNumber") int houseNumber,
                                  @RequestParam(name = "phone") String phone,
                                  Model model) {
        adminService.createShopAddress(country, city, postcode, street, houseNumber, phone);
        logger.info("Shop address saved");
        adminService.setStatsDefaultDate(model);
        return PAGE_NAME;
    }

    /**
     * Handle the request to "/editshop" URL to change data of the particular shop
     * @param model model for view displaying
     * @return name of the corresponding view
     */
    @PostMapping("/editshop")
    public String editShop(@RequestParam(name = "shopForEdit") int id,
                           @RequestParam(name = "country") String country,
                           @RequestParam(name = "city") String city,
                           @RequestParam(name = "postcode") int postcode ,
                           @RequestParam(name = "street") String street,
                           @RequestParam(name = "houseNumber") int houseNumber ,
                           @RequestParam(name = "phone") String phone,
                           Model model) {
        orderService.editShopById(id, country, city, postcode, street, houseNumber, phone);
        adminService.setStatsDefaultDate(model);
        return PAGE_NAME;
    }
}