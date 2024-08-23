package com.example.System.Services;

import com.example.System.Models.User;
import com.example.System.Repository.UserRepository;
import com.example.System.Security.Jwt.JwtService;
import com.example.System.Utils.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class UserService {

    private final UserRepository ur;
    private final AuthenticationManager am;
    private final JwtService jwts;
    private PasswordEncoder pe;
    private final  RandomNumberGenerator random;
    @Autowired
    public UserService(UserRepository ur, JwtService jwts, AuthenticationManager am, PasswordEncoder pe) {
        this.ur = ur;
        this.jwts = jwts;
        this.am = am;
        this.pe = pe;
        this.random = RandomNumberGenerator.getInstance();
    }

    public ResponseEntity<String> authenticateAndGenerateToken(String email, String password) {
        try {
            if(ur.findByEmail(email).isPresent()){
                User user = ur.findByEmail(email).get();
                if(pe.matches(password, user.getPassword())){
                    String token = jwts.generateToken(user.getEmail(), user.getUserId());
                    return ResponseEntity.ok(token);
                }
                return ResponseEntity.status(NOT_FOUND).body("Credentials do not match");
            }
            return ResponseEntity.status(NOT_FOUND).body("User not found");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("nashe");
        }
    }


    public ResponseEntity saveUser(User user) {
        try {
            if (ur.findByEmail(user.getEmail()).isPresent()) {
                // Evaluate if the username already exists...
                return ResponseEntity.status(CONFLICT).body("Already exist user whit the email  " + user.getEmail());
            }
            // Save user if the username don´t exists...
            user.setPassword(pe.encode(user.getPassword()));
            user.setUserId(random.generateUniqueId(ur));
            ur.save(user);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            // In case of an exception, return an INTERNAL_SERVER_ERROR response.
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity updateUser(Long id,String token, User user) {
        try {
            // Validate the JWT token. Check if the token is valid and not expired.
            if(!jwts.validateToken(token, user.getEmail())) {
                // If the token is expired or invalid, return an UNAUTHORIZED response.
                return ResponseEntity.status(UNAUTHORIZED).body("Token Expired");
            }
            else {
                if (!ur.existsById(id)) {
                    // If the user does not exist in the database, return an appropriate status.
                    return ResponseEntity.status(NOT_FOUND).body("User not found.");
                }

                User usr = ur.findById(id).get(); // get the user from the database...
                usr.setUserId(user.getUserId());
                usr.setTelephone(user.getTelephone());
                usr.setNickname(user.getNickname());
                usr.setSurname(user.getSurname());
                usr.setDateBirth(user.getDateBirth());
                // Update the fields in the user retrieved from the database.

                // Note: Password and email fields are not updated here because they require additional validation.
                return ResponseEntity.status(OK).body("User details updated succefully");
            }
        } catch (Exception e) {
            // In case of an exception, return an INTERNAL_SERVER_ERROR response.
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }

    }
    public ResponseEntity deleteUser(User user, String token){
        try {
            // Validate the JWT token. Check if the token is valid and not expired.
            if (!jwts.validateToken(token, user.getEmail())) {
                // If the token is expired or invalid, return an UNAUTHORIZED response.
                return ResponseEntity.status(UNAUTHORIZED).body("Token Expired");
            } else {
                // Check if the user exists in the database by their user ID.
                if (!ur.existsById(user.getUserId())) {
                    // If the user does not exist, return a NOT_FOUND response.
                    return ResponseEntity.status(NOT_FOUND).body("User not found");
                }
                // Delete the user from the database using their user ID.
                ur.deleteById(user.getUserId());
                return ResponseEntity.status(NO_CONTENT).build();
            }
        }
        catch(Exception e){
            // In case of an exception, return an INTERNAL_SERVER_ERROR response.
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }


}