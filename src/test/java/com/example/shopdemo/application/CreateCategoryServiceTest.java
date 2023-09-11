package com.example.shopdemo.application;

import com.example.shopdemo.models.Category;
import com.example.shopdemo.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCategoryServiceTest {

    private CategoryRepository categoryRepository;

    private CreateCategoryService createCategoryService;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        createCategoryService = new CreateCategoryService(categoryRepository);
    }

    @Test
    void createCategory() {
        createCategoryService.createCategory("CATEGORY");

        verify(categoryRepository).save(any(Category.class));
    }

}
