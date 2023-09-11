package com.example.shopdemo.dtos;

import com.example.shopdemo.models.Category;

public record AdminCategoryDetailDto(String id, String name, boolean hidden) {

    public static AdminCategoryDetailDto of(Category category) {
        return new AdminCategoryDetailDto(category.id().toString(), category.name(), category.hidden());
    }

}
