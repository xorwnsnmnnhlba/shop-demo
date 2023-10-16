package com.example.shopdemo.application;

import com.example.shopdemo.dtos.AdminUpdateProductDto;
import com.example.shopdemo.models.Product;
import com.example.shopdemo.models.ProductId;
import com.example.shopdemo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateProductService {

    private final ProductRepository productRepository;

    public void updateProduct(ProductId productId, AdminUpdateProductDto productDto) {
        Product product = productRepository.findById(productId).orElseThrow();
        product.update(productDto);
    }
}
