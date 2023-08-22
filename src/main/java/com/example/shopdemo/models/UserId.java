package com.example.shopdemo.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserId extends EntityId {

    public UserId(String value) {
        super(value);
    }

    public static UserId generate() {
        return new UserId(newTsid());
    }

}
