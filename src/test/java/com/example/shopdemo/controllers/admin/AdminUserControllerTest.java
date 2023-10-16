package com.example.shopdemo.controllers.admin;

import com.example.shopdemo.application.GetUserListService;
import com.example.shopdemo.application.GetUserService;
import com.example.shopdemo.controllers.ControllerTest;
import com.example.shopdemo.models.User;
import com.example.shopdemo.models.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.example.shopdemo.models.Role.ROLE_ADMIN;
import static com.example.shopdemo.models.Role.ROLE_USER;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdminUserController.class)
public class AdminUserControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserListService getUserListService;

    @MockBean
    private GetUserService getUserService;


    @Test
    @DisplayName("GET /admin/users")
    void list() throws Exception {
        List<User> users = List.of(new User(new UserId(USER_ID), "tester@example.com", "Tester", ROLE_USER),
                new User(new UserId(ADMIN_ID), "admin@example.com", "Admin", ROLE_ADMIN));

        given(getUserListService.getUserList()).willReturn(users);

        mockMvc.perform(get("/admin/users")
                        .header("Authorization", "Bearer " + adminAccessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name")))
                .andExpect(content().string(containsString("\"email\":\"admin@example.com\"")));
    }

    @Test
    @DisplayName("GET /admin/users/me - with admin access token")
    void detailAdmin() throws Exception {
        UserId userId = new UserId(ADMIN_ID);
        User user = new User(userId, "admin@example.com", "Admin", ROLE_ADMIN);

        given(getUserService.getAdminUser(userId)).willReturn(user);

        mockMvc.perform(get("/admin/users/me")
                        .header("Authorization", "Bearer " + adminAccessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name")));
    }

    @Test
    @DisplayName("GET /admin/users/me - with non-admin access token")
    void detailNonAdmin() throws Exception {
        mockMvc.perform(get("/admin/users/me")
                        .header("Authorization", "Bearer " + userAccessToken))
                .andExpect(status().isForbidden());
    }

}
