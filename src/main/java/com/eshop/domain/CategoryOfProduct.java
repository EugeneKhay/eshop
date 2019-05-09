package com.eshop.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
public class CategoryOfProduct {

    @Id
    @GeneratedValue
    private Integer id;

    private String categoryName;

    @OneToOne(mappedBy = "productCategory")
    @JsonBackReference
    private Product product;

    public CategoryOfProduct(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return categoryName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryOfProduct)) return false;
        CategoryOfProduct category = (CategoryOfProduct) o;
        return categoryName.equals(category.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName);
    }
}
