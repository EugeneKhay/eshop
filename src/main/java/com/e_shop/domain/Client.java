package com.e_shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;

    private String email;

    private String password;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private ClientAddress address;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Order> orders;

}
