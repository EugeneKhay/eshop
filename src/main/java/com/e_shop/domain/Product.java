package com.e_shop.domain;

import com.e_shop.domain.ProductParameteres;
import com.e_shop.enums.PaymentMethod;
import com.e_shop.enums.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Integer id;

    private String productName;

    private double productPrice;

    //@ElementCollection(targetClass = ProductCategory.class, fetch = FetchType.EAGER)
    //@CollectionTable(name = "payment_method", joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parameters_id")
    private ProductParameteres productParameteres;

    private double productWeight;

    private double productVolume;

    private int amount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Order order;
}
