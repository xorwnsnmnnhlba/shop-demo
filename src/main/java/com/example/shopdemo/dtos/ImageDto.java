package com.example.shopdemo.dtos;

import com.example.shopdemo.models.Image;

public record ImageDto(String url) {
    public static ImageDto of(Image image) {
        return new ImageDto(image.url());
    }
}
