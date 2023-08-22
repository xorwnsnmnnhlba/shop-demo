package com.example.shopdemo.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product_options")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOption extends BaseEntity {

    @EmbeddedId
    private ProductOptionId id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_option_id")
    @OrderBy("id")
    private List<ProductOptionItem> items = new ArrayList<>();

    public ProductOption(ProductOptionId id, String name, List<ProductOptionItem> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public ProductOptionId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public ProductOptionItem item(int index) {
        return items.get(index);
    }

    public int itemSize() {
        return items.size();
    }

    public ProductOptionItem itemById(ProductOptionItemId itemId) {
        return items.stream().filter(item -> Objects.equals(item.id(), itemId)).findFirst().orElseThrow();
    }
}
