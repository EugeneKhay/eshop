package com.eshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A bundle of product parameteres.
 */
@Entity
@Table(name = "parameters")
@Data
@NoArgsConstructor
public class ProductParameteres implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String colour;

    private String brand;

    private int weight;

    private String operatingSystem;

    @OneToOne(mappedBy = "productParameteres")
    @JsonBackReference
    private Product product;

    public ProductParameteres(String colour, String brand, int weight, String operatingSystem) {
        this.colour = colour;
        this.brand = brand;
        this.weight = weight;
        this.operatingSystem = operatingSystem;
    }

    @Override
    public String toString() {
        return "ProductParameteres{" +
                "id=" + id +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductParameteres)) return false;
        ProductParameteres that = (ProductParameteres) o;
        return id.equals(that.id);
    }
}
