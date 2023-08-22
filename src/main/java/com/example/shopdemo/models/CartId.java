package com.example.shopdemo.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartId extends EntityId {

    public CartId(String value) {
        super(value);
    }

    public static CartId generate() {
        return new CartId(newTsid());
    }
    
}