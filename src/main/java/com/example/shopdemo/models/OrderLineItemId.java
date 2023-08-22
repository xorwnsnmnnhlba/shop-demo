package com.example.shopdemo.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLineItemId extends EntityId {

    public OrderLineItemId(String value) {
        super(value);
    }

    public static OrderLineItemId generate() {
        return new OrderLineItemId(newTsid());
    }

}
