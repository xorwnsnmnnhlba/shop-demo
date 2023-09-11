package com.example.shopdemo.controllers.admin;

import com.example.shopdemo.application.CreateCategoryService;
import com.example.shopdemo.application.GetCategoryDetailService;
import com.example.shopdemo.application.GetCategoryListService;
import com.example.shopdemo.application.UpdateCategoryService;
import com.example.shopdemo.controllers.ControllerTest;
import com.example.shopdemo.models.Category;
import com.example.shopdemo.models.CategoryId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminCategoryController.class)
public class AdminCategoryControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCategoryListService getCategoryListService;

    @MockBean
    private GetCategoryDetailService getCategoryDetailService;

    @MockBean
    private CreateCategoryService createCategoryService;

    @MockBean
    private UpdateCategoryService updateCategoryService;

    @Test
    @DisplayName("GET /admin/categories")
    void list() throws Exception {
        CategoryId id = new CategoryId("0BV000CAT0001");
        Category category = new Category(id, "top");

        given(getCategoryListService.getAllCategories()).willReturn(List.of(category));

        mockMvc.perform(get("/admin/categories")
                        .header("Authorization", "Bearer " + adminAccessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("top")));
    }

    @Test
    @DisplayName("GET /admin/categories/{id}")
    void detail() throws Exception {
        CategoryId categoryId = CategoryId.generate();
        Category category = new Category(categoryId, "top", false);

        given(getCategoryDetailService.getCategory(categoryId)).willReturn(category);

        mockMvc.perform(get("/admin/categories/" + categoryId)
                        .header("Authorization", "Bearer " + adminAccessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("top")));
    }

    @Test
    @DisplayName("POST /admin/categories")
    void create() throws Exception {
        String json = """
                {
                    "name": "New Category"
                }
                """;

        mockMvc.perform(post("/admin/categories")
                        .header("Authorization", "Bearer " + adminAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(createCategoryService).createCategory("New Category");
    }

    @Test
    @DisplayName("PATCH /admin/categories")
    void update() throws Exception {
        CategoryId categoryId = CategoryId.generate();

        String json = """
                {
                    "name": "New Name",
                    "hidden": true
                }
                """;

        mockMvc.perform(patch("/admin/categories/" + categoryId)
                        .header("Authorization", "Bearer " + adminAccessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(updateCategoryService).updateCategory(categoryId, "New Name", true);
    }

}
