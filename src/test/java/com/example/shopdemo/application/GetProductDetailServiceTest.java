package com.example.shopdemo.application;

import com.example.shopdemo.Fixtures;
import com.example.shopdemo.dtos.AdminProductDetailDto;
import com.example.shopdemo.models.Category;
import com.example.shopdemo.models.CategoryId;
import com.example.shopdemo.models.Product;
import com.example.shopdemo.models.ProductId;
import com.example.shopdemo.repositories.CategoryRepository;
import com.example.shopdemo.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductDetailServiceTest {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private GetProductDetailService getProductDetailService;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        getProductDetailService = new GetProductDetailService(productRepository, categoryRepository);
    }

    @Test
    void getProductDetailDto() {
        Product product = Fixtures.product("맨투맨");
        ProductId productId = product.id();
        CategoryId categoryId = product.categoryId();
        Category category = new Category(categoryId, "카테고리", false);

        given(productRepository.findById(productId)).willReturn(Optional.of(product));
        given(categoryRepository.findById(categoryId)).willReturn(Optional.of(category));

        AdminProductDetailDto productDto = getProductDetailService.getAdminProductDetailDto(productId);

        assertThat(productDto.name()).isEqualTo("맨투맨");
    }
}