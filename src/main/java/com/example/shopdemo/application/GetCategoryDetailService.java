package com.example.shopdemo.application;

import com.example.shopdemo.models.Category;
import com.example.shopdemo.models.CategoryId;
import com.example.shopdemo.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetCategoryDetailService {

    private final CategoryRepository categoryRepository;


    public Category getCategory(CategoryId categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow();
    }
    
}
