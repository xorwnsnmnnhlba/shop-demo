package com.example.shopdemo.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderOptionId extends EntityId {

    public OrderOptionId(String value) {
        super(value);
    }

    public static OrderOptionId generate() {
        return new OrderOptionId(newTsid());
    }

}
