package org.example.shoe.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Categorys")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long category_id;
    private String name;

    @OneToMany(mappedBy = "product_id", cascade = CascadeType.ALL)
    private List<Product> product;

    public Category() {
    }

    public Category(Long category_id, String name) {
        this.category_id = category_id;
        this.name = name;
    }
    //    get and set

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
