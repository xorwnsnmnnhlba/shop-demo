package com.example.shopdemo.controllers.admin;

import com.example.shopdemo.application.CreateCategoryService;
import com.example.shopdemo.application.GetCategoryDetailService;
import com.example.shopdemo.application.GetCategoryListService;
import com.example.shopdemo.application.UpdateCategoryService;
import com.example.shopdemo.dtos.AdminCategoryDetailDto;
import com.example.shopdemo.dtos.AdminCategoryListDto;
import com.example.shopdemo.dtos.AdminCreateCategoryDto;
import com.example.shopdemo.dtos.AdminUpdateCategoryDto;
import com.example.shopdemo.models.Category;
import com.example.shopdemo.models.CategoryId;
import com.example.shopdemo.security.AdminRequired;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AdminRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final GetCategoryListService getCategoryListService;

    private final GetCategoryDetailService getCategoryDetailService;

    private final CreateCategoryService createCategoryService;

    private final UpdateCategoryService updateCategoryService;

    @GetMapping
    public AdminCategoryListDto list() {
        List<Category> categories = getCategoryListService.getAllCategories();
        return AdminCategoryListDto.of(categories);
    }

    @GetMapping("/{id}")
    public AdminCategoryDetailDto detail(@PathVariable String id) {
        CategoryId categoryId = new CategoryId(id);
        Category category = getCategoryDetailService.getCategory(categoryId);
        return AdminCategoryDetailDto.of(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody AdminCreateCategoryDto categoryDto) {
        createCategoryService.createCategory(categoryDto.name());
        return "Created";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable String id, @Valid @RequestBody AdminUpdateCategoryDto categoryDto) {
        updateCategoryService.updateCategory(new CategoryId(id), categoryDto.name(), categoryDto.hidden());
        return "Updated";
    }

}
