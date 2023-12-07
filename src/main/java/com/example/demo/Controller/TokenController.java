package com.example.demo.Controller;

import com.example.demo.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TokenController {
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard(@RequestHeader(name = "Authorization")
                                            String tokenHeader) {
        try {
            String token = tokenHeader.substring(7);
            if (jwtUtil.isValidToken(token)) {

                return ResponseEntity.ok("Token doğrulandı, Dashboard content");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token geçersiz");
            }
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token işlenirken bir hata oluştu");
        }
    }
}