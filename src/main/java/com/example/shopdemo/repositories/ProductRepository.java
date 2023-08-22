package com.example.shopdemo.repositories;

import com.example.shopdemo.models.CategoryId;
import com.example.shopdemo.models.Product;
import com.example.shopdemo.models.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, ProductId> {

    List<Product> findAll();

    List<Product> findAllByCategoryId(CategoryId categoryId);
}
