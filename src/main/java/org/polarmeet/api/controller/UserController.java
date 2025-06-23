package org.polarmeet.api.controller;

// controller are responsible for handling incoming web requests.

import org.polarmeet.api.entities.User;
import org.polarmeet.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE a user
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // READ all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers () {
       List<User> users = userService.getAllUsers();
       return ResponseEntity.ok(users);
    }

    // Get a single User with user ID
    // when you want to make an http request with a specific identifier
    // you need to include that in the url path.
    // http://example.com/home/11223344 --> might be a url to get a single home with id 11223344
    @GetMapping("/{id}") // receiving the id as path variable from the client
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // in this method we are accepting the id as path variable
        return userService.getUserById(id)
                .map(ResponseEntity::ok) // if user found, map to ResponseEntity.ok(user)
                .orElse(ResponseEntity.notFound().build()); // If not found, return 404 not found response to client
    }

    // Update an existing user
    // PUT http://localhost:8080/api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.updateUser(id, userDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a user
    // DELETE http://localhost:8080/api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

// Create a REST api endpoints for creating a user profile.
// Entity - DB TABLE
// Controller class
// Service class
// Repository class