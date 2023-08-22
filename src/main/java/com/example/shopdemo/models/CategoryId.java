package com.example.shopdemo.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryId extends EntityId {

    public CategoryId(String value) {
        super(value);
    }

    public static CategoryId generate() {
        return new CategoryId(newTsid());
    }

}
