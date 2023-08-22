package com.example.shopdemo.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

    @EmbeddedId
    private OrderId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "user_id"))
    private UserId userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    @OrderBy("id")
    private List<OrderLineItem> lineItems = new ArrayList<>();

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "total_price"))
    private Money totalPrice = Money.ZERO;

    @Embedded
    private Receiver receiver;

    @Embedded
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "ordered_at")
    private LocalDateTime orderedAt;

    public Order(OrderId id, UserId userId, List<OrderLineItem> lineItems,
                 Receiver receiver, Payment payment, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.lineItems = lineItems;
        this.totalPrice = lineItems.stream().map(OrderLineItem::totalPrice).reduce(Money::plus).orElseThrow();
        this.receiver = receiver;
        this.payment = payment;
        this.status = status;
        this.orderedAt = LocalDateTime.now();
    }

    public OrderId id() {
        return id;
    }

    public String title() {
        OrderLineItem lineItem = lineItems.get(0);
        int size = lineItems.size();
        String title = lineItem.productName();

        return (size > 1) ? title + " 외 " + (size - 1) : title;
    }

    public int lineItemSize() {
        return lineItems.size();
    }

    public OrderLineItem lineItem(int index) {
        return lineItems.get(index);
    }

    public Money totalPrice() {
        return totalPrice;
    }

    public OrderStatus status() {
        return status;
    }

    public LocalDateTime orderedAt() {
        return orderedAt;
    }

}
