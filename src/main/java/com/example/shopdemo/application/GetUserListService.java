package com.example.shopdemo.application;

import com.example.shopdemo.models.User;
import com.example.shopdemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetUserListService {

    private final UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.findAllByOrderByIdDesc();
    }
    
}
