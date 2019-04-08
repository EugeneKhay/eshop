package com.e_shop.domain;

import com.e_shop.enums.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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





//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name="order_product2",
//            joinColumns=@JoinColumn(name="productorder_id"),
//            inverseJoinColumns=@JoinColumn(name="order_id"))
//    private List<Order> orders;

//    @OneToOne(mappedBy = "productToOrder")
//    private Product productInOrder;
