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
    private double power;
    private String brand;

    @OneToOne//(mappedBy = "productparameters")
    private Product product;
}
