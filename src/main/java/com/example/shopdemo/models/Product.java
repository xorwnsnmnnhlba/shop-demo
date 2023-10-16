package com.example.shopdemo.models;

import com.example.shopdemo.dtos.AdminUpdateProductDto;
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

    private boolean hidden;

    public Product(ProductId id, CategoryId categoryId, List<Image> images, String name, Money price,
                   List<ProductOption> options, String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.images = new ArrayList<>(images);
        this.name = name;
        this.price = price;
        this.options = new ArrayList<>(options);
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

    public boolean hidden() {
        return hidden;
    }

    public ProductOption optionById(ProductOptionId optionId) {
        return options.stream().filter(option -> Objects.equals(option.id(), optionId)).findFirst().orElseThrow();
    }

    public void update(AdminUpdateProductDto productDto) {
        this.categoryId = new CategoryId(productDto.categoryId());
        updateImages(productDto.images());
        this.name = productDto.name();
        this.price = new Money(productDto.price());
        updateOptions(productDto.options());
        this.description = productDto.description();
        this.hidden = productDto.hidden();
    }

    private void updateImages(List<AdminUpdateProductDto.ImageDto> images) {
        this.images.removeIf(image -> {
            return images.stream().noneMatch(i -> image.id().toString().equals(i.id()));
        });

        images.forEach(image -> {
            if (image.id() == null) {
                this.images.add(new Image(ImageId.generate(), image.url()));
                return;
            }
            this.images.stream().filter(i -> i.id().toString().equals(image.id()))
                    .forEach(i -> i.changeUrl(image.url()));
        });
    }

    private void updateOptions(List<AdminUpdateProductDto.OptionDto> options) {
        this.options.removeIf(option -> {
            return options.stream().noneMatch(i -> option.id().toString().equals(i.id()));
        });

        options.forEach(option -> {
            if (option.id() == null) {
                this.options.add(new ProductOption(ProductOptionId.generate(), option.name(),
                        option.items().stream().map(
                                item -> new ProductOptionItem(ProductOptionItemId.generate(), item.name())).toList()));
                return;
            }
            this.options.stream().filter(i -> i.id().toString().equals(option.id()))
                    .forEach(i -> {
                        i.changeName(option.name());
                        i.updateItems(option.items());
                    });
        });
    }
}
