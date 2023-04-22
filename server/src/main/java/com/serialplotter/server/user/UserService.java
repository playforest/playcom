package com.serialplotter.server.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void insertUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email already exists");
        }

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long id, User user) {
        /*
         TODO:
          - validation for case where user ID is passed with an
            already existing email address
          -
        */
        Optional<User> userById = userRepository.findUserById(id);

        if (userById.isEmpty()) {
            throw new IllegalArgumentException("User ID [" + id + "] does not exist");
        }


        userRepository.updateUserById(id, user.getName(), user.getEmail(), user.getRole(),
                                       user.getLastlogin(), LocalDateTime.now(), user.getStatus());
    }

    public void removeUser(Long id) {
        Optional<User> userById = userRepository.findUserById(id);

        if (userById.isEmpty()) {
            throw new IllegalArgumentException("User ID [" + id + "] does not exist");
        }

        userRepository.deleteById(id);
    }
}
