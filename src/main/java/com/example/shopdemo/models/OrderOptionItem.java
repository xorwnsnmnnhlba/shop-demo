package com.example.shopdemo.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderOptionItem {

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "product_option_item_id"))
    private ProductOptionItemId productOptionItemId;

    @Column(name = "product_option_item_name")
    private String name;

    public OrderOptionItem(ProductOptionItem productOptionItem) {
        this.productOptionItemId = productOptionItem.id();
        this.name = productOptionItem.name();
    }

    public String name() {
        return name;
    }
    
}
