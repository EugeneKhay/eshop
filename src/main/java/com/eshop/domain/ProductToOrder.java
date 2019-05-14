package com.eshop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "product_to_order")
@Data
@NoArgsConstructor
public class ProductToOrder {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "product_id")
    private Product product;

    private int amount;

    public ProductToOrder(Order order, Product product, int amount) {
        this.order = order;
        this.product = product;
        this.amount = amount;
    }
}