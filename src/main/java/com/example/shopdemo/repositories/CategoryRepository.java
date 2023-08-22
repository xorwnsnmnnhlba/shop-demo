package com.example.shopdemo.repositories;

import com.example.shopdemo.models.Category;
import com.example.shopdemo.models.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, CategoryId> {
    List<Category> findAll();
}
