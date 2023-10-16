package com.example.shopdemo.application;

import com.example.shopdemo.models.*;
import com.example.shopdemo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateProductService {

    private final ProductRepository productRepository;

    public Product createProduct(CategoryId categoryId, List<Image> images, String name, Money price,
                                 List<ProductOption> options, String description) {
        ProductId id = ProductId.generate();
        Product product = new Product(id, categoryId, images, name, price, options, description);
        productRepository.save(product);
        return product;
    }
}
