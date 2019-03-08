package com.e_shop.controllers;

import com.e_shop.domain.Client;
import com.e_shop.domain.ClientAddress;
import com.e_shop.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;


@Controller
public class AddController {

    @Autowired
    private ClientService service;

    @GetMapping("/add")
    public String getadd() {
        return "add";
    }

    @PostMapping("/add")
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


//        String[] forDate = birthDate.split(" ");
//        LocalDate date = LocalDate.of(Integer.parseInt(forDate[0]), Integer.parseInt(forDate[1]), Integer.parseInt(forDate[2]));
//        Client client = new Client(firstName, lastName, date, passportData, address, email, password);
//        client.setContracts(Arrays.asList(new Contract()));
        service.saveClient(client);
        return "redirect:/";
    }
}
