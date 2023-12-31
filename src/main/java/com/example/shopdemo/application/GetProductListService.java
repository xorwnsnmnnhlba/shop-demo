package com.example.shopdemo.application;

import com.example.shopdemo.dtos.AdminProductListDto;
import com.example.shopdemo.dtos.AdminProductSummaryDto;
import com.example.shopdemo.dtos.ProductListDto;
import com.example.shopdemo.dtos.ProductSummaryDto;
import com.example.shopdemo.models.Category;
import com.example.shopdemo.models.CategoryId;
import com.example.shopdemo.models.Product;
import com.example.shopdemo.repositories.CategoryRepository;
import com.example.shopdemo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetProductListService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductListDto getProductListDto(String categoryId) {
        List<Product> products = findProducts(categoryId);
        List<ProductSummaryDto> productSummaryDtos = products.stream()
                .map(product -> {
                    Category category = categoryRepository.findById(product.categoryId()).orElseThrow();
                    return ProductSummaryDto.of(product, category);
                }).toList();

        return new ProductListDto(productSummaryDtos);
    }

    public List<Product> findProducts(String categoryId) {
        return (categoryId == null) ? productRepository.findAllByHiddenIsFalseOrderByIdAsc()
                : productRepository.findAllByCategoryIdAndHiddenIsFalseOrderByIdAsc(new CategoryId(categoryId));
    }

    public AdminProductListDto getAdminProductListDto() {
        List<Product> products = productRepository.findAllByOrderByIdAsc();
        List<AdminProductSummaryDto> productSummaryDtos = products.stream().map(product -> {
            Category category = categoryRepository.findById(product.categoryId()).orElseThrow();
            return AdminProductSummaryDto.of(product, category);
        }).toList();
        
        return new AdminProductListDto(productSummaryDtos);
    }
}
