package com.example.shopdemo.dtos;

import com.example.shopdemo.models.Category;
import com.example.shopdemo.models.Product;

public record AdminProductSummaryDto(String id, CategoryDto category, ImageDto thumbnail,
                                     String name, Long price, boolean hidden) {

    public static AdminProductSummaryDto of(Product product, Category category) {
        return new AdminProductSummaryDto(product.id().toString(), CategoryDto.of(category),
                ImageDto.of(product.image(0)), product.name(), product.price().asLong(), product.hidden());
    }

}
