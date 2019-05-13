package com.eshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clients_addresses")
@Data
@NoArgsConstructor
public class ClientAddress {

    @Id
    @GeneratedValue
    private Integer id;

    @Version
    //@Column(name="VERSION")
    private Integer version;

    private String country;
    private String city;
    private int postCode;
    private String street;
    private int houseNumber;
    private int flatNumber;

    //@LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "client_addresses",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> clients;


//    @OneToOne(mappedBy = "addressForDelivery")
//    private Order order;

    @OneToMany(mappedBy = "addressForDelivery", fetch = FetchType.EAGER)
    private List<Order> order;

    public ClientAddress(String country, String city, int postCode, String street, int houseNumber, int flatNumber) {
        this.country = country;
        this.city = city;
        this.postCode = postCode;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    @Override
    public String toString() {
        return  country + ", " + city + ", " + postCode + ", " + street
                + ", " + houseNumber + ", " + flatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientAddress)) return false;
        ClientAddress address = (ClientAddress) o;
        return postCode == address.postCode &&
                houseNumber == address.houseNumber &&
                flatNumber == address.flatNumber &&
                country.equals(address.country) &&
                city.equals(address.city) &&
                street.equals(address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, postCode, street, houseNumber, flatNumber);
    }
}



//    @OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
//    private List<Client> clientList;

//EXP
//    @ManyToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "client_id")
