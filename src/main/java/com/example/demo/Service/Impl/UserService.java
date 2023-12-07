package com.example.demo.Service.Impl;

import com.example.demo.Models.MyUser;
import com.example.demo.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean isUsernameTaken(String username) {
        MyUser existingUser = userRepository.findByUsername(username);
        return existingUser != null;
    }
    public UserService(UserRepository userRepository, PasswordEncoder
            passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public MyUser registerUser(MyUser user) {
        MyUser registerUser =new MyUser();
        registerUser.setUsername(user.getUsername());

        registerUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(registerUser);
    }
    public MyUser loginUser(String username, String password) {
        MyUser user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password,
                user.getPassword())) {
            return user;
        }
        return null;
    }
}
