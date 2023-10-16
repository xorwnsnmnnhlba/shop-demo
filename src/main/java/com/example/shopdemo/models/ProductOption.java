package com.example.shopdemo.models;

import com.example.shopdemo.dtos.AdminUpdateProductDto;
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
        this.items = new ArrayList<>(items);
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

    public void changeName(String name) {
        this.name = name;
    }

    public void updateItems(List<AdminUpdateProductDto.OptionItemDto> items) {
        this.items.removeIf(item -> {
            return items.stream().noneMatch(i -> item.id().toString().equals(i.id()));
        });

        items.forEach(item -> {
            if (item.id() == null) {
                this.items.add(new ProductOptionItem(ProductOptionItemId.generate(), item.name()));
                return;
            }
            this.items.stream().filter(i -> i.id().toString().equals(item.id()))
                    .forEach(i -> i.changeName(item.name()));
        });

    }
}
