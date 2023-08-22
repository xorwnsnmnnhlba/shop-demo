package com.example.shopdemo.application;

import com.example.shopdemo.models.Category;
import com.example.shopdemo.models.CategoryId;
import com.example.shopdemo.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class GetCategoryListServiceTest {

    private CategoryRepository categoryRepository;

    private GetCategoryListService getCategoryListService;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        getCategoryListService = new GetCategoryListService(categoryRepository);
    }

    @Test
    void list() {
        CategoryId id = new CategoryId("0BV000CAT0001");
        Category category = new Category(id, "top");

        given(categoryRepository.findAll()).willReturn(List.of(category));

        List<Category> categories = getCategoryListService.getCategories();
        assertThat(categories).hasSize(1);
    }

}