package com.example.shopdemo.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_line_items")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLineItem extends BaseEntity {

    @EmbeddedId
    private OrderLineItemId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "product_id"))
    private ProductId productId;

    @Column(name = "product_name")
    private String productName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_line_item_id")
    @OrderBy("id")
    private List<OrderOption> options = new ArrayList<>();

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "unit_price"))
    private Money unitPrice;

    private int quantity;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "total_price"))
    private Money totalPrice;

    public OrderLineItem(OrderLineItemId id, Product product, List<OrderOption> options, int quantity) {
        this.id = id;
        this.productId = product.id();
        this.productName = product.name();
        this.options = options;
        this.unitPrice = product.price();
        this.quantity = quantity;
        this.totalPrice = product.price().times(quantity);
    }

    public OrderLineItemId id() {
        return id;
    }

    public ProductId productId() {
        return productId;
    }

    public String productName() {
        return productName;
    }

    public int optionSize() {
        return options.size();
    }

    public OrderOption option(int index) {
        return options.get(index);
    }

    public Money unitPrice() {
        return unitPrice;
    }

    public int quantity() {
        return quantity;
    }

    public Money totalPrice() {
        return totalPrice;
    }

}
