package com.eshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Represents an address of particular shop.
 */
@Entity
@Table(name = "shops_addresses")
@Data
@NoArgsConstructor
public class ShopAddress implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String country;

    private String city;

    private int postCode;

    private String street;

    private int houseNumber;

    private String phoneNumber;

    @OneToMany(mappedBy = "addressForSelfCollect", fetch = FetchType.EAGER)
    private List<Order> order;

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




