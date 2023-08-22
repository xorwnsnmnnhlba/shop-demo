package com.example.shopdemo.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @EmbeddedId
    private ProductId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "category_id"))
    private CategoryId categoryId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @OrderBy("id")
    private List<Image> images = new ArrayList<>();

    private String name;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price"))
    private Money price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    @OrderBy("id")
    private List<ProductOption> options = new ArrayList<>();

    private String description;

    public Product(ProductId id, CategoryId categoryId, List<Image> images, String name, Money price,
                   List<ProductOption> options, String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.images = images;
        this.name = name;
        this.price = price;
        this.options = options;
        this.description = description;
    }

    public ProductId id() {
        return id;
    }

    public CategoryId categoryId() {
        return categoryId;
    }

    public int imageSize() {
        return images.size();
    }

    public Image image(int index) {
        return images.get(index);
    }

    public String name() {
        return name;
    }

    public Money price() {
        return price;
    }

    public int optionSize() {
        return options.size();
    }

    public ProductOption option(int index) {
        return options.get(index);
    }

    public String description() {
        return description;
    }

    public ProductOption optionById(ProductOptionId optionId) {
        return options.stream().filter(option -> Objects.equals(option.id(), optionId)).findFirst().orElseThrow();
    }
}
