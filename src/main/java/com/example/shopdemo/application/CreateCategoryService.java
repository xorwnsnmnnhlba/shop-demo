package com.example.shopdemo.application;

import com.example.shopdemo.models.Category;
import com.example.shopdemo.models.CategoryId;
import com.example.shopdemo.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateCategoryService {

    private final CategoryRepository categoryRepository;

    public void createCategory(String name) {
        Category category = new Category(CategoryId.generate(), name, true);
        categoryRepository.save(category);
    }

}
