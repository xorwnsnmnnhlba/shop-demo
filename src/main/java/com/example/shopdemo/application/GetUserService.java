package com.example.shopdemo.application;

import com.example.shopdemo.models.User;
import com.example.shopdemo.models.UserId;
import com.example.shopdemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetUserService {

    private final UserRepository userRepository;

    public User getUser(UserId id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getAdminUser(UserId id) {
        return userRepository.findById(id).filter(User::isAdmin).orElseThrow();
    }
}
