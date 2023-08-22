package com.example.shopdemo.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "carts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseEntity {

    @EmbeddedId
    private CartId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id"))
    private UserId userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    @OrderBy("id")
    private List<CartLineItem> lineItems = new ArrayList<>();

    public Cart(UserId userId) {
        this.userId = userId;
    }

    public Cart(CartId id, UserId userId) {
        this.id = id;
        this.userId = userId;
    }

    public int lineItemSize() {
        return lineItems.size();
    }

    public CartLineItem lineItem(int index) {
        return lineItems.get(index);
    }

    public void clear() {
        lineItems.clear();
    }

    public void addProduct(Product product, Set<CartLineItemOption> options, int quantity) {
        Optional<CartLineItem> found = findLineItem(product, options);

        if (found.isPresent()) {
            CartLineItem lineItem = found.orElseThrow();
            lineItem.increaseQuantity(quantity);
            return;
        }

        CartLineItem lineItem = createLineItem(product, options, quantity);
        lineItems.add(lineItem);
    }

    private Optional<CartLineItem> findLineItem(Product product, Set<CartLineItemOption> options) {
        return lineItems.stream().filter(lineItem -> lineItem.sameProduct(product.id(), options)).findFirst();
    }

    private CartLineItem createLineItem(Product product, Set<CartLineItemOption> options, int quantity) {
        CartLineItemId id = CartLineItemId.generate();
        return new CartLineItem(id, product.id(), options, quantity);
    }
}
