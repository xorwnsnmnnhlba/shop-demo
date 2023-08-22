package com.example.shopdemo.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "order_options")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderOption extends BaseEntity {

    @EmbeddedId
    private OrderOptionId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "product_option_id"))
    private ProductOptionId productOptionId;

    private String name;

    @Embedded
    private OrderOptionItem optionItem;

    public OrderOption(OrderOptionId id, ProductOption productOption, ProductOptionItem productOptionItem) {
        this.id = id;
        this.productOptionId = productOption.id();
        this.name = productOption.name();
        this.optionItem = new OrderOptionItem(productOptionItem);
    }

    public static OrderOption of(ProductOption productOption, ProductOptionItem productOptionItem) {
        OrderOptionId id = OrderOptionId.generate();
        return new OrderOption(id, productOption, productOptionItem);
    }

    public String name() {
        return name;
    }

    public OrderOptionItem optionItem() {
        return optionItem;
    }

}
