package com.example.shopdemo.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductId extends EntityId {

    public ProductId(String value) {
        super(value);
    }

    public static ProductId generate() {
        return new ProductId(newTsid());
    }

}
