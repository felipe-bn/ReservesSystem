package com.example.System.Utils;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import java.util.Random;

public class RandomNumberGenerator {
    /*
         * RandomNumberGenerator is a singleton class responsible for generating unique random numbers
         * for entity IDs in the application. The class ensures that only one instance of the random number
         * generator is created using the Singleton pattern.
         *
         * The class uses SecureRandom to generate random numbers and guarantees that the generated IDs
         * are unique within the context of a provided repository. The uniqueness is verified by checking
         * the repository to ensure that the generated ID does not already exist.
         *
         * Key Points:
         * 1. **Singleton Pattern**: Ensures a single instance of RandomNumberGenerator throughout the application.
         * 2. **SecureRandom**: Used for generating cryptographically strong random numbers.
         * 3. **generateUniqueId Method**: Generates a random ID and checks its uniqueness against the provided repository.
         * 4. **Repository Parameter**: The `generateUniqueId` method takes a repository as a parameter to check for ID uniqueness.
         *
         * Usage:
         * - Instantiate RandomNumberGenerator using `RandomNumberGenerator.getInstance()`.
         * - Call `generateUniqueId(repository)` to obtain a unique ID for a new entity.
         * - Ensure that the repository provided can check for ID existence using the `existsById` method.
     */

    private static RandomNumberGenerator instance;
    private static SecureRandom random;

    private RandomNumberGenerator(){
        random = new SecureRandom();
    }
    public static RandomNumberGenerator getInstance(){
        if(instance == null){
            // In this way, you are only creating a single instance of SecureRandom.
            instance = new RandomNumberGenerator();
        }
        return instance;
    }
    public Long generateUniqueId(Object repository){
        try{
            Long id;
            do {
                id = generateRandomNumber();
            } while(existInRepository(id, repository));
            return id;
        } catch (Exception e) {
            throw new RuntimeException("Error generating unique ID.", e);
        }

    }

    private Long generateRandomNumber() {
        return random.nextLong(900000) + 100000;
    }

    private boolean existInRepository(Long id, Object repository) throws Exception{
        if(repository != null){
            try{
                return (boolean) repository.getClass().getMethod("existsById", Long.class).invoke(repository, id);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException("Method 'existsById' not found in the repository.", e);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Error invoking the method 'existsById' in the repository.", e);
            } catch (Exception e) {
                throw new RuntimeException("An unexpected error occurred while checking the repository.", e);
            }
        }
        return false;
    }
}
