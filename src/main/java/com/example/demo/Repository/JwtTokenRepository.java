package com.example.demo.Repository;

import com.example.demo.Models.JwtToken;
import com.example.demo.Models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    JwtToken findByUser(MyUser user);
}
