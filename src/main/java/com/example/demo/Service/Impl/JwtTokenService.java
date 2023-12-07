package com.example.demo.Service.Impl;

import com.example.demo.Models.JwtToken;
import com.example.demo.Models.MyUser;
import com.example.demo.Repository.JwtTokenRepository;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {
    private final JwtTokenRepository jwtTokenRepository;
    public JwtTokenService(JwtTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;
    }
    public JwtToken saveJwtToken(JwtToken jwtToken) {
        return jwtTokenRepository.save(jwtToken);
    }
    public JwtToken findByUser(MyUser user) {
        return jwtTokenRepository.findByUser(user);
    }
}
