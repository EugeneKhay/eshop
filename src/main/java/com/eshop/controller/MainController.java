package com.eshop.controller;

import com.eshop.domain.*;
import com.eshop.jms.MessageSender;
import com.eshop.service.ClientService;
import com.eshop.service.OrderService;
import com.eshop.service.ProductService;
import com.eshop.service.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class MainController {

    private static String CLIENT_ATTR = "client";

    private static String PERSONAL_PAGE = "personal";

    @Autowired
    private ClientService clientService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MessageSender sender;

    private Basket basket;

    private Logger logger = Logger.getLogger("logger");

    @GetMapping("/")
    public String homepage(HttpSession session, Model model) {
        if (basket == null) basket = new Basket();
        session.setAttribute("shop_basket", basket);
        List<Product> bestsellers = orderService.getBestsellers();
        model.addAttribute("items", bestsellers);
        model.addAttribute("categories", productService.getAllCategories());
        //TODO replace
        sender.sendMessage("Bestsellers updated");
        return "homepage2";
    }

    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(@RequestParam(name = "firstName") String firstName,
                      @RequestParam(name = "lastName") String lastName,
                      @RequestParam(name = "birthDate") @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate birthDate,
                      @RequestParam(name = "email") String email,
                      @RequestParam(name = "phone") String phone,
                      @RequestParam(name = "password") String password) {
        clientService.registerNewClient(firstName, lastName, birthDate, email, phone, password);
        return "redirect:/";
    }

    @GetMapping("/personal")
    public String enterMyAccount(Model model) {
        Client clientById = clientService.getClientForView();
        model.addAttribute(CLIENT_ATTR, clientById);
        return PERSONAL_PAGE;
    }

    @PostMapping("/edit")
    public String editClientData(@RequestParam(name = "clientForEdit") int clientId,
                                 @RequestParam(name = "first") String firstName,
                                 @RequestParam(name = "last") String lastName,
                                 @RequestParam(name = "password") String password,
                                 @RequestParam(name = "email") String email,
                                 @RequestParam(name = "phone") String phone,
                                 Model model) {
        Client clientForView = clientService.editClientPersonalData(clientId, firstName, lastName, password, email, phone);
        model.addAttribute(CLIENT_ATTR, clientForView);
        return PERSONAL_PAGE;
    }

    @PostMapping("/addaddress")
    public String addClientAddress (@RequestParam(name = "country", required = false) String country,
                                    @RequestParam(name = "city", required = false) String city,
                                    @RequestParam(name = "postcode", required = false) int postcode,
                                    @RequestParam(name = "street", required = false) String street,
                                    @RequestParam(name = "houseNumber", required = false) int houseNumber,
                                    @RequestParam(name = "flatNumber", required = false) int flatNumber,
                                    Model model) {
        Client clientForView = clientService.createAddressForClient(country, city, postcode, street, houseNumber, flatNumber);
        logger.info("Save address");
        model.addAttribute(CLIENT_ATTR, clientForView);
        model.addAttribute("addresses", clientForView.getAddressList());
        return PERSONAL_PAGE;
    }

    @PostMapping("/editaddress")
    public String editClientAddress (@RequestParam(name = "addressForEdit") int addressId,
                                     @RequestParam(name = "country") String country,
                                     @RequestParam(name = "city") String city,
                                     @RequestParam(name = "postcode") int postcode,
                                     @RequestParam(name = "street") String street,
                                     @RequestParam(name = "houseNumber") int houseNumber,
                                     @RequestParam(name = "flatNumber") int flatNumber,
                                     Model model) {
        Client clientForView = clientService.editAddressForClient(addressId, country, city, postcode, street, houseNumber, flatNumber);
        model.addAttribute(CLIENT_ATTR, clientForView);
        return PERSONAL_PAGE;
    }

    @PostMapping("/deleteaddress")
    public String deleteClientAddress(@RequestParam(name = "addressForDelete") int id, Model model) {
        clientService.deleteAddressById(id);
        Client clientById = clientService.getClientForView();
        model.addAttribute(CLIENT_ATTR, clientById);
        return PERSONAL_PAGE;
    }

    @PostMapping("/editproduct")
    public String editProduct(@RequestParam(name = "productForEdit") int productId,
                              @RequestParam(name = "name") String productName,
                              @RequestParam(name = "brand") String brand,
                              @RequestParam(name = "price") double price,
                              @RequestParam(name = "amount") int amount,
                              @RequestParam(name = "category") String category,
                              @RequestParam(name = "color") String colour,
                              @RequestParam(name = "weight") int weight,
                              @RequestParam(name = "operatingSystem") String operatingSystem,
                              Model model) {
        productService.editProductByAdmin(productId, productName, brand, price, amount, category, colour, weight, operatingSystem);
        adminService.setStatsDefaultDate(model);
        return "adminpage";
    }

    @GetMapping("/{category}")
    public String get(@PathVariable(name = "category") String page, Model model) {

        //TODO move to service
        String end = page.substring(1);
        String start = page.substring(0,1).toUpperCase();
        String res = start + end;
        CategoryOfProduct category = new CategoryOfProduct(res);

        List<Product> allProductsByCategory = productService.getAllProductsByCategory(res);
        model.addAttribute("items", allProductsByCategory);
        model.addAttribute("pageName", category.getCategoryName());
        model.addAttribute("categories", productService.getAllCategories());
        return "products";
    }
}