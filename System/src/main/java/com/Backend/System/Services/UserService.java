package com.Backend.System.Services;

import com.Backend.System.DTO.UserDTO;
import com.Backend.System.Models.User;
import com.Backend.System.Repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class UserService {

    private final UserRepository ur;

    @Autowired
    public UserService(UserRepository ur) {
        this.ur = ur;
    }

    public List<User> getAll() {
        try {
            return (List<User>) ur.findAll();
        } catch (Exception e) {
            return (List<User>) new ResponseEntity<>(INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity saveUser(User user) {
        try {
            if (ur.findByUsername(user.getUsername()).isPresent()) {
                // Evaluate if the username already exists...
                return ResponseEntity.status(CONFLICT).body("Already exist user whit the username: " + user.getUsername());
            }
            // Save user if the username don´t exists...
            ur.save(user);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity updateUser(Long id, @Valid UserDTO user) {
        try {
            if (!ur.existsById(id)) {
                // If the user does not exist in the database, return an appropriate status.
                return ResponseEntity.status(NOT_FOUND).body("User not found.");
            }

            User usr = ur.findById(id).get(); // get the user from the database...
            usr.setUserId(user.userId);
            usr.setUsername(user.username);
            usr.setTelephone(user.telephone);
            // Update the fields in the user retrieved from the database.

            // Note: Password and email fields are not updated here because they require additional validation.
            return ResponseEntity.status(OK).body("User details updated succefully");
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

    }
    public ResponseEntity deleteUser(Long id){
        try {
            if(!ur.existsById(id)){
                return ResponseEntity.status(NOT_FOUND).body("User not found");
            }
            ur.deleteById(id);
            return ResponseEntity.status(NO_CONTENT).build();
        }
        catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

}
