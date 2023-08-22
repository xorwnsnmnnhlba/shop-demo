package com.example.shopdemo.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptionId extends EntityId {

    public ProductOptionId(String value) {
        super(value);
    }

    public static ProductOptionId generate() {
        return new ProductOptionId(newTsid());
    }

}
