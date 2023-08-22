package com.example.shopdemo.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageId extends EntityId {

    public ImageId(String value) {
        super(value);
    }

    public static ImageId generate() {
        return new ImageId(newTsid());
    }
}
