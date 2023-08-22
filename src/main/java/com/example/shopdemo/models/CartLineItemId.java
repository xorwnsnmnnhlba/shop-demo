package com.example.shopdemo.models;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartLineItemId extends EntityId {

    public CartLineItemId(String value) {
        super(value);
    }

    public static CartLineItemId generate() {
        return new CartLineItemId(newTsid());
    }
}
