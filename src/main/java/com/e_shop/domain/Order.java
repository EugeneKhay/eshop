package com.e_shop.domain;

import com.e_shop.enums.DeliveryMethod;
import com.e_shop.enums.OrderStatus;
import com.e_shop.enums.PaymentMethod;
import com.e_shop.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    private LocalDate dateOfOrder;

    private double sumOfOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductToOrder> orderProducts = new ArrayList<>();

    //EXP
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private ClientAddress addressForDelivery;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(Client client, PaymentMethod paymentMethod, DeliveryMethod deliveryMethod, PaymentStatus paymentStatus, OrderStatus orderStatus) {
        this.client = client;
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable (name="order_product",
//            joinColumns=@JoinColumn (name="order_id"),
//            inverseJoinColumns=@JoinColumn(name="product_id"))
//    private Collection<Product> productsInOrder;


//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable (name="order_product2",
//            joinColumns=@JoinColumn (name="order_id"),
//            inverseJoinColumns=@JoinColumn(name="productorder_id"))
//    private Collection<ProductToOrder> productsInOrder;
