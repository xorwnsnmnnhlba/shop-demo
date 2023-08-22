package com.example.shopdemo.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderId extends EntityId {

    public OrderId(String value) {
        super(value);
    }

    public static OrderId generate() {
        return new OrderId(newTsid());
    }
}
