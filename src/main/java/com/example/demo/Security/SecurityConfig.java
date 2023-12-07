package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
            Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
            /*    .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/static/**", "/register",
                                        "/login","/api/dashboard").permitAll()
                                .anyRequest().authenticated()
                )*/
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/**").permitAll()
                )
            //    .formLogin(form -> form.loginPage("/login.html").permitAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
