package domain;

import domain.enums.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private ProductCategory category;
    private ProductParameteres productParameteres;
    private double productWeight;
    private double productVolume;
    private int amount;
}
