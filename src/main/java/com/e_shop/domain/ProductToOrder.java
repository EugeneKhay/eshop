//package com.e_shop.domain;
//
//import com.e_shop.enums.ProductCategory;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.List;
//import java.util.Objects;

//@Entity
//@Table(name = "producttoorder")
//@Data
//@NoArgsConstructor
//public class ProductToOrder {
//
//    @Id
//    @GeneratedValue
//    private Integer id;
//
//    private Product product;
//
//    private int amountInOrder;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name="order_product",
//            joinColumns=@JoinColumn(name="product_id"),
//            inverseJoinColumns=@JoinColumn(name="order_id"))
//    private List<Order> orders;
//
//}
