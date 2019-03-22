package com.e_shop.controllers;

import com.e_shop.domain.Basket;
import com.e_shop.domain.Client;
import com.e_shop.domain.ClientAddress;
import com.e_shop.enums.Role;
import com.e_shop.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
//@SessionAttributes("shop_basket")
public class MainController {

    @Autowired
    private ClientService clientService;

    Basket basket;


    @GetMapping("/")
    public String homepage(HttpSession session) {
        if (basket == null) basket = new Basket();
        session.setAttribute("shop_basket", basket);
        return "homepage2";
    }

    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(@RequestParam(name = "firstName") String firstName,
                      @RequestParam(name = "lastName") String lastName,
                      @RequestParam(name = "birthDate") String birthDate,
                      @RequestParam(name = "email") String email,
                      @RequestParam(name = "password") String password,
                      @RequestParam(name = "country") String country,
                      @RequestParam(name = "city") String city,
                      @RequestParam(name = "postcode") int postcode,
                      @RequestParam(name = "street") String street,
                      @RequestParam(name = "house") int house,
                      @RequestParam(name = "flat") int flat
    ) {
        String[] array = birthDate.split(" ");
        int[] intArr = Arrays.stream(array).mapToInt(Integer::valueOf).toArray();
        LocalDate birth = LocalDate.of(intArr[0], intArr[1], intArr[2]);

        ClientAddress address = new ClientAddress();
        address.setCountry(country);
        address.setCity(city);
        address.setPostCode(postcode);
        address.setStreet(street);
        address.setHouseNumber(house);
        address.setFlatNumber(flat);

        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setBirthDate(birth);
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
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Client client = (Client) auth.getPrincipal();
        model.addAttribute("client", client);
        System.out.println(client);
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

        ClientAddress newAddress = new ClientAddress();
        newAddress.setCountry(country);
        newAddress.setCity(city);
        newAddress.setPostCode(postcode);
        newAddress.setStreet(street);
        newAddress.setHouseNumber(houseNumber);
        newAddress.setFlatNumber(flatNumber);

        client.setAddress(newAddress);

        clientService.saveClient(client);
        model.addAttribute("client", client);
        System.out.println(client.getFirstName());
        System.out.println(client.getLastName());
        return "personal";
    }
}





//    @ModelAttribute
//    public Basket createBasket(){
//        return new Basket();
//    }

    //    @PostMapping("/logaut")
//    public String logout(HttpSession session, SessionStatus status) {
//        session.removeAttribute("shop_basket");
//        status.setComplete();
//        return "homepage";
//    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }

//    @PostMapping("/login")
//    public String login(@RequestParam(name="username") String firstName,
//                        HttpSession session) {
//        Client currentClient = clientService.getClientByName(firstName);
//        session.setAttribute("client", currentClient);
//                System.out.println(currentClient.getFirstName());
//        return "login";
//    }




