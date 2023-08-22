package com.example.shopdemo.application;

import com.example.shopdemo.models.*;
import com.example.shopdemo.repositories.CategoryRepository;
import com.example.shopdemo.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetProductListServiceTest {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    private GetProductListService getProductListService;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        productRepository = mock(ProductRepository.class);
        getProductListService = new GetProductListService(productRepository, categoryRepository);
    }

    @Test
    void list() {
        CategoryId categoryId = new CategoryId("0BV000CAT0001");
        Category category = new Category(categoryId, "top");

        Image image = new Image(new ImageId("0BV000IMG0001"),
                "https://ahastudio.github.io/my-image-assets/images/cbcl-products/01.jpg");

        ProductOptionItem blackColorOption = new ProductOptionItem(new ProductOptionItemId("0BV000ITEM0001"), "Black");
        ProductOptionItem whiteColorOption = new ProductOptionItem(new ProductOptionItemId("0BV000ITEM0002"), "White");
        ProductOption productColorOption = new ProductOption(new ProductOptionId("0BV000OPT0001"),
                "Color", List.of(blackColorOption, whiteColorOption));

        ProductOptionItem smallSizeOption = new ProductOptionItem(new ProductOptionItemId("0BV000ITEM0003"), "S");
        ProductOptionItem mediumSizeOption = new ProductOptionItem(new ProductOptionItemId("0BV000ITEM0004"), "M");
        ProductOptionItem largeSizeOption = new ProductOptionItem(new ProductOptionItemId("0BV000ITEM0005"), "L");
        ProductOption productSizeOption = new ProductOption(new ProductOptionId("0BV000OPT0002"),
                "Size", List.of(smallSizeOption, mediumSizeOption, largeSizeOption));

        ProductId productId = new ProductId("0BV000PRO0001");
        Product product = new Product(productId, categoryId, List.of(image), "Product Top", new Money(100_000L),
                List.of(productColorOption, productSizeOption), "Product Top Description");

        given(categoryRepository.findAll()).willReturn(List.of(category));
        given(productRepository.findAll()).willReturn(List.of(product));
    }
}