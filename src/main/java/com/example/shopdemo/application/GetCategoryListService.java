package com.example.shopdemo.application;

import com.example.shopdemo.models.Category;
import com.example.shopdemo.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetCategoryListService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAllByHiddenIsFalseOrderByIdAsc();
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAllByOrderByIdAsc();
    }

}
