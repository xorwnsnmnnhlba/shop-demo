package com.example.shopdemo.dtos;

import java.util.List;

public record CategoryListDto(
        List<CategoryDto> categories
) {
}
