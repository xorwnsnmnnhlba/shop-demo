package com.example.shopdemo.repositories;

import com.example.shopdemo.models.CategoryId;
import com.example.shopdemo.models.Product;
import com.example.shopdemo.models.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, ProductId> {

    List<Product> findAllByOrderByIdAsc();

    List<Product> findAllByCategoryId(CategoryId categoryId);

    List<Product> findAllByHiddenIsFalseOrderByIdAsc();

    List<Product> findAllByCategoryIdAndHiddenIsFalseOrderByIdAsc(CategoryId categoryId);
}
