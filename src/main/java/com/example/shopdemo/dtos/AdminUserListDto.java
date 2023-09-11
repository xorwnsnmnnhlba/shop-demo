package com.example.shopdemo.dtos;

import com.example.shopdemo.models.User;

import java.util.List;

public record AdminUserListDto(List<UserDto> users) {

    public static AdminUserListDto of(List<User> users) {
        return new AdminUserListDto(users.stream().map(user ->
                new UserDto(user.id().toString(), user.name(), user.email(),
                        user.role().toString().replace("ROLE_", ""))).toList());
    }

    public record UserDto(String id, String name, String email, String role) {

    }
    
}
