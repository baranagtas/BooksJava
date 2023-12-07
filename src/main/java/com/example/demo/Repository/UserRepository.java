package com.example.demo.Repository;

import com.example.demo.Models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByUsername(String username);
}