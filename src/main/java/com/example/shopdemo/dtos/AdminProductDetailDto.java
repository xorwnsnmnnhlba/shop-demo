package com.example.shopdemo.dtos;

import com.example.shopdemo.models.Category;
import com.example.shopdemo.models.Product;

import java.util.List;
import java.util.stream.IntStream;

public record AdminProductDetailDto(String string, CategoryDto category, List<ImageDto> images, String name, Long price,
                                    List<ProductOptionDto> options, String description, boolean hidden) {

    public static AdminProductDetailDto of(Product product, Category category) {
        return new AdminProductDetailDto(product.id().toString(), CategoryDto.of(category),
                IntStream.range(0, product.imageSize())
                        .mapToObj(index -> ImageDto.of(product.image(index))).toList(),
                product.name(), product.price().asLong(),
                IntStream.range(0, product.optionSize())
                        .mapToObj(index -> ProductOptionDto.of(product.option(index))).toList(),
                product.description(), product.hidden());
    }

}
