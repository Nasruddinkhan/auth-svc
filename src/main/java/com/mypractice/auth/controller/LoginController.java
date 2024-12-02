package com.mypractice.auth.controller;

import com.mypractice.auth.exception.UserNotFoundException;
import com.mypractice.auth.model.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class LoginController {

    @MutationMapping
    public LoginResponse login(@Argument String username, @Argument String password) {
        log.info("LoginController.login username = {}, password = {}", username, password);
        if ("admin".equals(username) && "password123".equals(password)) {
            return new LoginResponse("dummy-jwt-token", "Login successful");
        }
        throw new UserNotFoundException(String.format("""
                User not found for the given ID: %s""", username));
    }
}
