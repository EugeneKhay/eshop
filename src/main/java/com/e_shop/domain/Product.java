package com.e_shop.domain;

import com.e_shop.domain.ProductParameteres;
import com.e_shop.enums.PaymentMethod;
import com.e_shop.enums.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parameters_id")
    private ProductParameteres productParameteres;

    public Product(String productName, double productPrice, int amount, String imagePath, ProductCategory category, ProductParameteres productParameteres) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.amount = amount;
        this.imagePath = imagePath;
        this.category = category;
        this.productParameteres = productParameteres;
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




//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name="order_product",
//            joinColumns=@JoinColumn(name="product_id"),
//            inverseJoinColumns=@JoinColumn(name="order_id"))
//    private List<Order> orders;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "prodtoorder_id")
//    private ProductToOrder productToOrder;
