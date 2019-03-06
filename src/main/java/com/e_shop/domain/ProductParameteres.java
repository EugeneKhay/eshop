package com.e_shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "parameters")
@Data
@NoArgsConstructor
public class ProductParameteres {

    @Id
    @GeneratedValue
    private Integer id;

    private String colour;

    private String brand;

    @OneToOne(mappedBy = "productParameteres")
    private Product product;

    public ProductParameteres(String colour, String brand) {
        this.colour = colour;
        this.brand = brand;
    }
}
