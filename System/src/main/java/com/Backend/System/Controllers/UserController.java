package com.Backend.System.Controllers;

import com.Backend.System.DTO.UserDTO;
import com.Backend.System.Models.User;
import com.Backend.System.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService us;
    @GetMapping("")
    public List<User> GetAll(){
        return (List<User>) us.getAll();
    }
    @PostMapping("")
    public ResponseEntity saveUser(@RequestBody User user){
        return us.saveUser(user);
    }
    @PutMapping("/{id}/actualizar")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserDTO user){
        return us.updateUser(id,user);
    }
    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity deleteUser(@PathVariable Long id){
        return us.deleteUser(id);
    }
}
