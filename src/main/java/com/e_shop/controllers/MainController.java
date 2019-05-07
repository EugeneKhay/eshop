package com.e_shop.controllers;

import com.e_shop.domain.*;
import com.e_shop.enums.ProductCategory;
import com.e_shop.enums.Role;
import com.e_shop.exception.LoginException;
//import com.e_shop.jms.Producer;
//import com.e_shop.jms.MessageSender;
import com.e_shop.jms.MessageSender;
import com.e_shop.services.ClientService;
import com.e_shop.services.OrderService;
import com.e_shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Controller
public class MainController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MessageSender sender;

    Basket basket;

    private Logger logger = Logger.getLogger("logger");


    @GetMapping("/")
    public String homepage(HttpSession session, Model model) {
        if (basket == null) basket = new Basket();
        session.setAttribute("shop_basket", basket);
        List<Product> bestsellers = orderService.getBestsellers();
        model.addAttribute("items", bestsellers);
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
                      @RequestParam(name = "password") String password)
//                      @RequestParam(name = "country") String country,
//                      @RequestParam(name = "city") String city,
//                      @RequestParam(name = "postcode") int postcode,
//                      @RequestParam(name = "street") String street,
//                      @RequestParam(name = "house") int house,
//                      @RequestParam(name = "flat") int flat)
    {
        clientService.registerNewClient(firstName, lastName, birthDate, email, password);
                                        //country, city, postcode, street, house, flat);
        return "redirect:/";
    }

    @GetMapping("/personal")
    public String enter(Model model) {
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("client", client);
        return "personal";
    }

    //user editing
    @PostMapping("/edit")
    public String editClientData(@RequestParam(name = "clientForEdit") int clientId,
                                 @RequestParam(name = "first") String firstName,
                                 @RequestParam(name = "last") String lastName,
                                 @RequestParam(name = "password") String password,
                                 @RequestParam(name = "email") String email,
//                                 @RequestParam(name = "country", required = false) String country,
//                                 @RequestParam(name = "city", required = false) String city,
//                                 @RequestParam(name = "postcode", required = false) Integer postcode,
//                                 @RequestParam(name = "street", required = false) String street,
//                                 @RequestParam(name = "houseNumber", required = false) Integer houseNumber,
//                                 @RequestParam(name = "flatNumber", required = false) Integer flatNumber,
                                 Model model) {
        Client client = clientService.editClientPersonalData(clientId, firstName, lastName, password, email);
                                                             //country, city, postcode, street, houseNumber, flatNumber);
        model.addAttribute("client", client);
        return "personal";
    }

    @PostMapping("/addaddress")
    public String addClientAddress (@RequestParam(name = "country", required = false) String country,
                                     @RequestParam(name = "city", required = false) String city,
                                     @RequestParam(name = "postcode", required = false) Integer postcode,
                                     @RequestParam(name = "street", required = false) String street,
                                     @RequestParam(name = "houseNumber", required = false) Integer houseNumber,
                                     @RequestParam(name = "flatNumber", required = false) Integer flatNumber,
                                     Model model) {
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientAddress address = new ClientAddress();
        address.setCountry(country);
        address.setCity(city);
        address.setPostCode(postcode);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setFlatNumber(flatNumber);
        List<ClientAddress> addresses = client.getAddressList();
        addresses.add(address);
        clientService.saveAddress(address);
        client.setAddressList(addresses);
        clientService.saveClient(client);
        logger.info("Try to save address");
        model.addAttribute("client", client);
        model.addAttribute("addresses", addresses);
        return "basket";
    }

    @PostMapping("/editaddress")
    public String editClientAddress (@RequestParam(name = "addressForEdit") int addressId,
                                     @RequestParam(name = "country") String country,
                                     @RequestParam(name = "city") String city,
                                     @RequestParam(name = "postcode") String postcode,
                                     @RequestParam(name = "street") String street,
                                     @RequestParam(name = "houseNumber") String houseNumber,
                                     @RequestParam(name = "flatNumber") String flatNumber,
                                     Model model) {
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientAddress newAddress = clientService.getAddressById(addressId);
        logger.info("Old address " + newAddress + " retrieved");
        newAddress.setCountry(country);
        newAddress.setCity(city);
        newAddress.setPostCode(Integer.valueOf(postcode));
        newAddress.setStreet(street);
        newAddress.setHouseNumber(Integer.valueOf(houseNumber));
        newAddress.setFlatNumber(Integer.valueOf(flatNumber));
        clientService.saveAddress(newAddress);
        logger.info("New address " + newAddress + " saved");
        List<ClientAddress> addresses = client.getAddressList();
        addresses.remove(clientService.getAddressById(addressId));
        addresses.add(newAddress);
        client.setAddressList(addresses);
        model.addAttribute("client", client);
        return "personal";
    }

    @PostMapping("/editproduct")
    public String editProduct(@RequestParam(name = "productForEdit") int productId,
                                 @RequestParam(name = "name") String productName,
                                 @RequestParam(name = "brand") String brand,
                                 @RequestParam(name = "price") double price,
                                 @RequestParam(name = "amount") int amount,
                                 @RequestParam(name = "category") String category,
                                 @RequestParam(name = "color") String colour,
                                 Model model) {
        ProductCategory category1 = ProductCategory.valueOf(category);
        ProductParameteres parameteres = new ProductParameteres(colour, brand);
        Product product = productService.getProductById(productId);
        product.setProductName(productName);
        product.setProductPrice(price);
        product.setAmount(amount);
        product.setCategory(category1);
        product.setProductParameteres(parameteres);
        productService.saveProduct(product);
        model.addAttribute("products", productService.getAllProducts());
        return "adminpage";
    }
}







