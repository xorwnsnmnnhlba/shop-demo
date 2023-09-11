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
public class UpdateCategoryService {

    private final CategoryRepository categoryRepository;

    public void updateCategory(CategoryId id, String name, boolean hidden) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.changeName(name);

        if (hidden) {
            category.hide();
        } else {
            category.show();
        }
    }
}
