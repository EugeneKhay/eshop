package com.eshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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
    @JsonBackReference
    private Product product;

    public ProductParameteres(String colour, String brand) {
        this.colour = colour;
        this.brand = brand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductParameteres)) return false;
        ProductParameteres that = (ProductParameteres) o;
        return id.equals(that.id) &&
                colour.equals(that.colour) &&
                brand.equals(that.brand) &&
                product.equals(that.product);
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
}
