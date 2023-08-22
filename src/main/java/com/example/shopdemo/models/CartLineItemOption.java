package com.example.shopdemo.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class CartLineItemOption {

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "product_option_id"))
    private ProductOptionId productOptionId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "product_option_item_id"))
    private ProductOptionItemId productOptionItemId;

    public ProductOptionId productOptionId() {
        return productOptionId;
    }

    public ProductOptionItemId productOptionItemId() {
        return productOptionItemId;
    }

}
