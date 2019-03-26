package com.e_shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clients_addresses")
@Data
@NoArgsConstructor
public class ClientAddress {

    @Id
    @GeneratedValue
    private Integer id;

    private String country;
    private String city;
    private int postCode;
    private String street;
    private int houseNumber;
    private int flatNumber;

    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
    private List<Client> clientList;

    public ClientAddress(String country, String city, int postCode, String street, int houseNumber, int flatNumber) {
        this.country = country;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }
}
