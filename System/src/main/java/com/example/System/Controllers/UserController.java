package com.example.System.Controllers;

import com.example.System.Models.User;
import com.example.System.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService us;

    @PutMapping("/save")
    public ResponseEntity saveUser(@RequestBody User user){
        return us.saveUser(user);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity deleteUser(@RequestBody User user, @RequestHeader("Authorization") String token){
        return us.deleteUser(user, token);
    }

    @PostMapping("/{id}/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user, @RequestHeader("Authorization") String token){
        return us.updateUser(id, token, user);
    }
}
