package com.example.System.Controllers;

import com.example.System.Models.User;
import com.example.System.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService us;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody User loginRequest){
        if (loginRequest.getEmail().isBlank() || loginRequest.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }
        return us.authenticateAndGenerateToken(loginRequest.getEmail(), loginRequest.getPassword());
    }

}
