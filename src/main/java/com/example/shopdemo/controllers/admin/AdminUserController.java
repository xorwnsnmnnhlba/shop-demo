package com.example.shopdemo.controllers.admin;

import com.example.shopdemo.application.GetUserListService;
import com.example.shopdemo.application.GetUserService;
import com.example.shopdemo.dtos.AdminUserListDto;
import com.example.shopdemo.dtos.UserDto;
import com.example.shopdemo.models.User;
import com.example.shopdemo.models.UserId;
import com.example.shopdemo.security.AdminRequired;
import com.example.shopdemo.security.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AdminRequired
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
public class AdminUserController {

    private final GetUserListService getUserListService;

    private final GetUserService getUserService;

    @GetMapping
    public AdminUserListDto list() {
        List<User> users = getUserListService.getUserList();
        return AdminUserListDto.of(users);
    }

    @GetMapping("/me")
    public UserDto me(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UserId id = new UserId(authUser.id());
        User user = getUserService.getAdminUser(id);
        return UserDto.of(user);
    }

}
