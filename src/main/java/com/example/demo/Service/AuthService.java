package com.example.demo.Service;

import com.example.demo.Models.Dto.SignupDTO;
import com.example.demo.Models.Dto.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}