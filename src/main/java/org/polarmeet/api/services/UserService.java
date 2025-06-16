package org.polarmeet.api.services;

import org.polarmeet.api.entities.User;
import org.polarmeet.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // marks this class as a spring service component
public class UserService {

    // Dependency on UserRepository
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE
    // This method is to create a user in the DB with given data in request coming from the controller
    public User createUser(User user) {
       return userRepository.save(user);
    }

    // READ
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ only one user with ID
    public Optional<User> getUserById(Long id) {
       // Optional is used to handle cases where a user might not be found
        return userRepository.findById(id);
    }

    // UPDATE
    public Optional<User> updateUser(Long id, User userDetails) {
       return userRepository.findById(id)
               .map(existingUser -> {
                   existingUser.setName(userDetails.getName());
                   existingUser.setEmail(userDetails.getEmail());
                   return userRepository.save(existingUser);
               });
    }

    // DELETE
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        // if user is not found.
        return false;
    }

}
