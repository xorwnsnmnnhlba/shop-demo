package com.example.shopdemo.controllers;

import com.example.shopdemo.application.GetCategoryListService;
import com.example.shopdemo.dtos.CategoryDto;
import com.example.shopdemo.dtos.CategoryListDto;
import com.example.shopdemo.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final GetCategoryListService getCategoryListService;

    @GetMapping
    public CategoryListDto list() {
        List<Category> categories = getCategoryListService.getCategories();
        List<CategoryDto> categoryDtos = categories.stream()
                .map(this::mapOfDto).toList();

        return new CategoryListDto(categoryDtos);
    }

    private CategoryDto mapOfDto(Category category) {
        return new CategoryDto(category.id().toString(), category.name());
    }

}
