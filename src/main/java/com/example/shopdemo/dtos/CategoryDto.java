package com.example.shopdemo.dtos;

import com.example.shopdemo.models.Category;

public record CategoryDto(String id, String name) {
    public static CategoryDto of(Category category) {
        return new CategoryDto(category.id().toString(), category.name());
    }
}
