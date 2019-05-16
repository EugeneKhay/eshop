package com.eshop.domain;

import com.eshop.enums.DeliveryMethod;
import com.eshop.enums.OrderStatus;
import com.eshop.enums.PaymentMethod;
import com.eshop.enums.PaymentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents all related data of a particular order.
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private LocalDate dateOfOrder;

    private double sumOfOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    //TODO check
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private  Set<ProductToOrder> orderProducts = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private  ClientAddress addressForDelivery;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_self_id")
    private  ShopAddress addressForSelfCollect;

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