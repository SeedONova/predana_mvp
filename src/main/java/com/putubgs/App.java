package com.putubgs;

import com.putubgs.model.User;
import com.putubgs.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.putubgs.util.SecurityUtil;

import java.util.List;

public class App {
    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.createUser("putu", "putu@example.com", "securepassword");

        boolean isMatch = userService.validateUserPassword("putu", "securepassword");
        System.out.println("Password match: " + isMatch);
    }
}
