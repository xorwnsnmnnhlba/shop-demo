package com.example.shopdemo.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_option_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionItem extends BaseEntity {

    @EmbeddedId
    private ProductOptionItemId id;

    private String name;

    public ProductOptionItem(ProductOptionItemId id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductOptionItemId id() {
        return id;
    }

    public String name() {
        return name;
    }
}
