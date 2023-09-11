package com.example.shopdemo.application;

import com.example.shopdemo.models.User;
import com.example.shopdemo.models.UserId;
import com.example.shopdemo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.shopdemo.models.Role.ROLE_ADMIN;
import static com.example.shopdemo.models.Role.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserListServiceTest {

    private UserRepository userRepository;

    private GetUserListService getUserListService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        getUserListService = new GetUserListService(userRepository);
    }

    @Test
    @DisplayName("getUserList")
    void getUserList() {
        UserId id1 = UserId.generate();
        UserId id2 = UserId.generate();

        given(userRepository.findAllByOrderByIdDesc()).willReturn(List.of(
                new User(id2, "user2@example.com", "User 2", ROLE_USER),
                new User(id1, "user1@example.com", "User 1", ROLE_ADMIN)
        ));

        List<User> users = getUserListService.getUserList();

        assertThat(users).hasSize(2);
    }

}