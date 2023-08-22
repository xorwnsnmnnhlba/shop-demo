package com.example.shopdemo.repositories;

import com.example.shopdemo.models.User;
import com.example.shopdemo.models.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UserId> {

    boolean existsByEmail(String email);

}
