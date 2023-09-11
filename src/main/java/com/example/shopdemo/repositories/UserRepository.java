package com.example.shopdemo.repositories;

import com.example.shopdemo.models.User;
import com.example.shopdemo.models.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, UserId> {

    List<User> findAllByOrderByIdDesc();

    List<User> findAllByIdIn(List<UserId> userIds);

    boolean existsByEmail(String email);
    
}
