package com.example.shopdemo.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cart_line_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CartLineItem extends BaseEntity {

    @EmbeddedId
    private CartLineItemId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "product_id"))
    private ProductId productId;

    @ElementCollection
    @CollectionTable(name = "cart_line_item_options", joinColumns = @JoinColumn(name = "cart_line_item_id"))
    private Set<CartLineItemOption> options = new HashSet<>();

    private int quantity = 0;

    public CartLineItemId id() {
        return id;
    }

    public ProductId productId() {
        return productId;
    }

    public int optionSize() {
        return options.size();
    }

    public List<ProductOptionId> optionIds() {
        return options.stream().map(CartLineItemOption::productOptionId).toList();
    }

    public ProductOptionItemId productOptionItemId(ProductOptionId optionId) {
        return options.stream().filter(cartLineItemOption -> cartLineItemOption.productOptionId().equals(optionId))
                .map(CartLineItemOption::productOptionItemId).findFirst().orElseThrow();
    }

    public int quantity() {
        return quantity;
    }

    public boolean sameProduct(ProductId productId, Set<CartLineItemOption> cartLineItemOptions) {
        return this.productId.equals(productId) && this.options.equals(cartLineItemOptions);
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

}
