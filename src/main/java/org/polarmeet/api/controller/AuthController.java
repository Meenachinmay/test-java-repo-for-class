package org.polarmeet.api.controller;

import org.polarmeet.api.entities.User;
import org.polarmeet.api.repository.UserRepository;
import org.polarmeet.api.request.AuthRequest;
import org.polarmeet.api.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth") // http:localhost:8080/api/auth/login
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {

        // Find user by email
        Optional<User> userOptional = userRepository.findByEmail(authRequest.getEmail());

        if (userOptional.isPresent()) {
            // if the user is present then save it in a local variable
          User user = userOptional.get();
          if (user.getPassword().equals(authRequest.getPassword())) {
              // password match, generate token
              String token = jwtService.generateToken(user);
              System.out.println("reached till token");
              return ResponseEntity.ok(token);
          }
        }

        // if user not found or password does not match
        return ResponseEntity.status(401).body("Invalid credentials");
    }

}
