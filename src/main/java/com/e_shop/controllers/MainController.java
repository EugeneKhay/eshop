package com.e_shop.controllers;

import com.e_shop.domain.Basket;
import com.e_shop.domain.Client;
import com.e_shop.domain.ClientAddress;
import com.e_shop.enums.Role;
import com.e_shop.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

@Controller
//@SessionAttributes("shop_basket")
public class MainController {

    @Autowired
    private ClientService service;

    Basket basket;

//    @GetMapping("/")
//    public ModelAndView homepage(@ModelAttribute Basket basket) {
//        ModelAndView modelAndView = new ModelAndView();
//        //if (basket == null) basket = new Basket();
//        modelAndView.addObject("shop_basket", new Basket());
//        modelAndView.setViewName("homepage2");
//        return modelAndView;
//    }

    @GetMapping("/")
    public String homepage(HttpSession session) {

        //ModelAndView modelAndView = new ModelAndView();
        if (basket == null) basket = new Basket();
        session.setAttribute("shop_basket", basket);
        //modelAndView.addObject("shop_basket", new Basket());
        //modelAndView.setViewName("homepage2");
        return "homepage2";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/registration")
    public String add(@RequestParam(name="firstName") String firstName,
                      @RequestParam(name="lastName") String lastName,
                      @RequestParam(name="birthDate") String birthDate,
                      @RequestParam(name="email") String email,
                      @RequestParam(name="password") String password,

                      @RequestParam(name="country") String country,
                      @RequestParam(name="city") String city,
                      @RequestParam(name="postcode") int postcode,
                      @RequestParam(name="street") String street,
                      @RequestParam(name="house") int house,
                      @RequestParam(name="flat") int flat
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
        client.setRoles(Collections.singleton(Role.ADMIN));

        service.saveClient(client);
        return "redirect:/";
    }

    @GetMapping("/personal")
    public String enter(Model model) {
//        Client client =
//        model.addAttribute("client", client);
        return "personal";
    }

//    @ModelAttribute
//    public Basket createBasket(){
//        return new Basket();
//    }
}



