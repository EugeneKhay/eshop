package com.eshop.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * An item presented in the product catalog and available for booking.
 */
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private String productName;

    private double productPrice;

    private int amount;

    private String imagePath;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parameters_id")
    @JsonManagedReference
    private ProductParameteres productParameteres;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private CategoryOfProduct productCategory;

    public Product(String productName, double productPrice, int amount, String imagePath, ProductParameteres productParameteres, CategoryOfProduct productCategory) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.amount = amount;
        this.imagePath = imagePath;
        this.productParameteres = productParameteres;
        this.productCategory = productCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return  id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }
}