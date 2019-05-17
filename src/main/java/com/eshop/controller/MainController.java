package com.eshop.controller;

import com.eshop.domain.*;
import com.eshop.jms.MessageSender;
import com.eshop.service.AdminService;
import com.eshop.service.ClientService;
import com.eshop.service.OrderService;
import com.eshop.service.ProductService;
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

/**
 * Controller class for main user actions handling: registration, go to private account, edit user's data.
 */
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

    /**
     * Handle the request to "/" URL to go to the home page
     * @param session current HTTP session
     * @param model model for view
     * @return name of the corresponding view
     */
    @GetMapping("/")
    public String homepage(HttpSession session, Model model) {
        if (basket == null) basket = new Basket();
        session.setAttribute("shop_basket", basket);
        List<Product> bestsellers = orderService.getBestsellers();
        model.addAttribute("items", bestsellers);
        model.addAttribute("categories", productService.getAllCategories());
        sender.sendMessage("Update");
        return "homepage2";
    }

    /**
     * Handle the request to "/registration" URL to go to the registration page
     * @return name of the corresponding view
     */
    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    /**
     * Handle the request to "/registration" URL to add new user
     * @param firstName first name of the new client
     * @param lastName last name of the new client
     * @param birthDate birth date of the new client
     * @param email email of the new client
     * @param phone phone number of the new client
     * @param password password of the new client
     * @return name of the corresponding view
     */
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

    /**
     * Handle the request to "/personal" URL to go to the user's account
     * @param model model for view
     * @return name of the corresponding view
     */
    @GetMapping("/personal")
    public String enterMyAccount(Model model) {
        Client clientById = clientService.getClientForView();
        model.addAttribute(CLIENT_ATTR, clientById);
        return PERSONAL_PAGE;
    }

    /**
     * Handle the request to "/edit" URL to edit client's data
     * @param clientId client's id
     * @param firstName new first name
     * @param lastName new last name
     * @param password new password
     * @param email new email
     * @param phone new phone number
     * @param model model for view
     * @return name of the corresponding view
     */
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

    /**
     * Handle the request to "/addaddress" URL to add new client's address
     * @param country country of new address
     * @param city country of new address
     * @param postcode post code of new address
     * @param street street of new address
     * @param houseNumber house number of new address
     * @param flatNumber flat number of new address
     * @param model model for view
     * @return name of the corresponding view
     */
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

    /**
     * Handle the request to "/editaddress" URL to edit client's address
     * @param addressId id of address to edit
     * @param country country of new address
     * @param city country of new address
     * @param postcode post code of new address
     * @param street street of new address
     * @param houseNumber house number of new address
     * @param flatNumber flat number of new address
     * @param model model for view
     * @return name of the corresponding view
     */
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

    /**
     * Handle the request to "/deleteaddress" URL to delete client's address
     * @param id id of address to delete
     * @param model model for view
     * @return name of the corresponding view
     */
    @PostMapping("/deleteaddress")
    public String deleteClientAddress(@RequestParam(name = "addressForDelete") int id, Model model) {
        clientService.deleteAddressById(id);
        Client clientById = clientService.getClientForView();
        model.addAttribute(CLIENT_ATTR, clientById);
        return PERSONAL_PAGE;
    }

    /**
     * Handle the request to "/editproduct" URL to edit the particulac product by manager
     * @param productId id of product to edit
     * @param productName new name of the product
     * @param brand new brand of the product
     * @param price new price of the product
     * @param amount new amount of the product
     * @param category new category of the product
     * @param colour new colour of the product
     * @param weight new weight of the product
     * @param operatingSystem new OS of the product
     * @param model model for view
     * @return name of the corresponding view
     */
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

    /**
     * Handle the request to get data for catalog
     * @param page name of the page
     * @param model model for view
     * @return name of the corresponding view
     */
    @GetMapping("/{category}")
    public String get(@PathVariable(name = "category") String page, Model model) {
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