package com.example.shopdemo.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionItemId extends EntityId {

    public ProductOptionItemId(String value) {
        super(value);
    }

    public static ProductOptionItemId generate() {
        return new ProductOptionItemId(newTsid());
    }

}
