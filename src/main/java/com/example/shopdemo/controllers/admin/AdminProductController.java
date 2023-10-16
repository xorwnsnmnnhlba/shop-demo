package com.example.shopdemo.controllers.admin;


import com.example.shopdemo.application.CreateProductService;
import com.example.shopdemo.application.GetProductDetailService;
import com.example.shopdemo.application.GetProductListService;
import com.example.shopdemo.application.UpdateProductService;
import com.example.shopdemo.dtos.AdminCreateProductDto;
import com.example.shopdemo.dtos.AdminProductDetailDto;
import com.example.shopdemo.dtos.AdminProductListDto;
import com.example.shopdemo.dtos.AdminUpdateProductDto;
import com.example.shopdemo.models.*;
import com.example.shopdemo.security.AdminRequired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AdminRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class AdminProductController {

    private final GetProductListService getProductListService;

    private final GetProductDetailService getProductDetailService;

    private final CreateProductService createProductService;

    private final UpdateProductService updateProductService;

    @GetMapping
    public AdminProductListDto list() {
        return getProductListService.getAdminProductListDto();
    }

    @GetMapping("/{id}")
    public AdminProductDetailDto detail(@PathVariable String id) {
        return getProductDetailService.getAdminProductDetailDto(new ProductId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody AdminCreateProductDto productDto) {
        createProductService.createProduct(new CategoryId(productDto.categoryId()), mapToImages(productDto.images()),
                productDto.name(), new Money(productDto.price()),
                mapToProductOptions(productDto.options()), productDto.description());
        return "Created";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable String id, @Valid @RequestBody AdminUpdateProductDto productDto) {
        updateProductService.updateProduct(new ProductId(id), productDto);
        return "Updated";
    }

    private List<Image> mapToImages(List<AdminCreateProductDto.ImageDto> imageDtos) {
        return imageDtos.stream().map(image -> new Image(ImageId.generate(), image.url())).toList();
    }

    private List<ProductOption> mapToProductOptions(List<AdminCreateProductDto.OptionDto> optionDtos) {
        return optionDtos.stream().map(option -> new ProductOption(ProductOptionId.generate(),
                option.name(), mapToProductOptionItems(option.items()))).toList();
    }

    private List<ProductOptionItem> mapToProductOptionItems(List<AdminCreateProductDto.OptionItemDto> optionItemDtos) {
        return optionItemDtos.stream().map(optionItem -> new ProductOptionItem(ProductOptionItemId.generate(),
                optionItem.name())).toList();
    }

}
