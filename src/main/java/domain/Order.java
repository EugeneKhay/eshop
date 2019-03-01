package domain;

import domain.enums.DeliveryMethod;
import domain.enums.OrderStatus;
import domain.enums.PaymentMethod;
import domain.enums.PaymentStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    private Client client;
    private ClientAddress clientAddress;
    private PaymentMethod paymentMethod;
    private DeliveryMethod deliveryMethod;
    private List<Product> productsInOrder;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
}
