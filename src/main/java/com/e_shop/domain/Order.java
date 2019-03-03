package com.e_shop.domain;

import com.e_shop.enums.DeliveryMethod;
import com.e_shop.enums.OrderStatus;
import com.e_shop.enums.PaymentMethod;
import com.e_shop.enums.PaymentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> productsInOrder;

    //private ClientAddress clientAddress;

    //@ElementCollection(targetClass = PaymentMethod.class, fetch = FetchType.EAGER)
    //@CollectionTable(name = "payment_method", joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    //@ElementCollection(targetClass = DeliveryMethod.class, fetch = FetchType.EAGER)
    //@CollectionTable(name = "delivery_method", joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    //@ElementCollection(targetClass = PaymentStatus.class, fetch = FetchType.EAGER)
    //@CollectionTable(name = "payment_status", joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    //@ElementCollection(targetClass = OrderStatus.class, fetch = FetchType.EAGER)
    //@CollectionTable(name = "order_status", joinColumns = @JoinColumn(name = "order_id"))
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
