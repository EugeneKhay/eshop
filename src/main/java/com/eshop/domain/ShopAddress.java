package com.eshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shops_addresses")
@Data
@NoArgsConstructor
public class ShopAddress {

    @Id
    @GeneratedValue
    private Integer id;

    private String country;
    private String city;
    private int postCode;
    private String street;
    private int houseNumber;
    private String phoneNumber;

//    @OneToOne(mappedBy = "addressForSelfCollect")
//    private Order order;

    @OneToMany(mappedBy = "addressForSelfCollect", fetch = FetchType.EAGER)
    private List<Order> order;


    //    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
//    private List<Client> clientList;

//EXP
//    @ManyToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "client_id")




    public ShopAddress(String country, String city, int postCode, String street, int houseNumber, String phoneNumber) {
        this.country = country;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  country + ", " + city + ", " + postCode + ", " + street
                + ", " + houseNumber + ", " + phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopAddress)) return false;
        ShopAddress that = (ShopAddress) o;
        return postCode == that.postCode &&
                houseNumber == that.houseNumber &&
                country.equals(that.country) &&
                city.equals(that.city) &&
                street.equals(that.street) &&
                phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, postCode, street, houseNumber, phoneNumber);
    }
}




