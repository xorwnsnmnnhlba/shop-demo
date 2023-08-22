package com.example.shopdemo.application;

import com.example.shopdemo.dtos.ProductDetailDto;
import com.example.shopdemo.models.Category;
import com.example.shopdemo.models.CategoryId;
import com.example.shopdemo.models.Product;
import com.example.shopdemo.models.ProductId;
import com.example.shopdemo.repositories.CategoryRepository;
import com.example.shopdemo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductDetailService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductDetailDto getProductDetailDto(String productId) {
        ProductId id = new ProductId(productId);
        Product product = productRepository.findById(id).orElseThrow();
        Category category = categoryRepository.findById(product.categoryId()).orElseThrow();
        return ProductDetailDto.of(product, category);
    }

    public List<Product> findProducts(String categoryId) {
        return (categoryId == null) ? productRepository.findAll()
                : productRepository.findAllByCategoryId(new CategoryId(categoryId));
    }
}
