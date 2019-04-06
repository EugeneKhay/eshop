package com.e_shop.controllers;

import com.e_shop.domain.*;
import com.e_shop.enums.ProductCategory;
import com.e_shop.enums.Role;
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
import java.util.*;

@Controller
public class MainController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    Basket basket;

    @GetMapping("/")
    public String homepage(HttpSession session, Model model) {
        if (basket == null) basket = new Basket();
        session.setAttribute("shop_basket", basket);
//        List<Product> allProducts = productService.getAllProducts();
        List<Product> allProducts = orderService.getBestsellers();
        model.addAttribute("items", allProducts);
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
                      @RequestParam(name = "password") String password,
                      @RequestParam(name = "country") String country,
                      @RequestParam(name = "city") String city,
                      @RequestParam(name = "postcode") int postcode,
                      @RequestParam(name = "street") String street,
                      @RequestParam(name = "house") int house,
                      @RequestParam(name = "flat") int flat) {
        ClientAddress address = new ClientAddress(country, city, postcode, street, house,flat);
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setBirthDate(birthDate);
        client.setEmail(email);
        client.setPassword(password);
        client.setAddress(address);
        client.setRoles(Collections.singleton(Role.ROLE_USER));
        clientService.saveClient(client);
        return "redirect:/";
    }

    /*
     * client data - from security context
     */
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
                                 @RequestParam(name = "country") String country,
                                 @RequestParam(name = "city") String city,
                                 @RequestParam(name = "postcode") int postcode,
                                 @RequestParam(name = "street") String street,
                                 @RequestParam(name = "houseNumber") int houseNumber,
                                 @RequestParam(name = "flatNumber") int flatNumber,
                                 Model model) {
        Client client = clientService.getClientById(clientId);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPassword(password);
        client.setEmail(email);
        ClientAddress newAddress = new ClientAddress(country, city, postcode, street, houseNumber, flatNumber);
        client.setAddress(newAddress);
        clientService.saveClient(client);
        model.addAttribute("client", client);
        return "personal";
    }

    @PostMapping("/editproduct")
    public String editClientData(@RequestParam(name = "productForEdit") int productId,
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







