package com.example.demo.Controller;

import com.example.demo.Models.MyUser;
import com.example.demo.Security.JwtUtil;
import com.example.demo.Service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserService userService;
    private JwtUtil jwtUtil;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody MyUser myUser) {
        if (userService.isUsernameTaken(myUser.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken.");
        }
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        userService.registerUser(myUser);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> loginUser(@RequestBody MyUser user) {
        Optional<MyUser> authenticatedUser = userService.loginUser(user.getUsername(), user.getPassword());

        String token = authenticatedUser.map(authUser -> jwtUtil.generateToken(authUser.getUsername()))
                .orElse(null);

        if (token != null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }
}
