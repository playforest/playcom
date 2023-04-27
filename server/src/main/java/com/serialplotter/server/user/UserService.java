package com.serialplotter.server.user;

import jakarta.transaction.Transactional;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    /*
    TODO:
    - send back meaningful response payloads
     */

    private final UserRepository userRepository;
    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found";
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        Optional<User> userOptional = userRepository.findUserById(userId);

        if (userOptional.isPresent()){
            User user = userOptional.get();
            return user;
        } else {
            throw new IllegalArgumentException("User ID [" + userId + "] does not exist");
        }
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE)));
    }

    public void insertUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());

        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email already exists");
        }

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long userId, User updatedUser) {
        /*
         TODO:
          - validation for case where user ID is passed with an
            already existing email address
          - email validation (both format and duplicates)
          - research cleaner way of handling validation
          - check for already existing username
        */
        Optional<User> userOptional = userRepository.findUserById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (updatedUser.getName().length() > 3) {
                user.setName(updatedUser.getName());
            } else {
                throw new IllegalArgumentException("username needs to be at least 3 characters long");
            }

            user.setEmail(updatedUser.getEmail());
            user.setRole(updatedUser.getRole());
            user.setStatus(updatedUser.getStatus());

            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User ID [" + userId + "] does not exist");
        }
    }

    @Transactional
    public void partialUpdateUser(Long userId, Map<String, Object> updates) {
        Optional<User> userOptional = userRepository.findUserById(userId);

        if(userOptional.isEmpty()) {
            throw new IllegalArgumentException("User ID [" + userId + "] does not exist");
        } else {
            User user = userOptional.get();

            updates.forEach((property, value) -> {
                try {
                    PropertyUtils.setProperty(user, property, value);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException("Error updating property " + property, e);
                }
            });
            userRepository.save(user);
        }
    }

    public void removeUser(Long userId) {
        boolean userExists = userRepository.existsById(userId);

        if (!userExists) {
            throw new IllegalArgumentException("User ID [" + userId + "] does not exist");
        }
        /*
        TODO:
        - change function from deleting user from db to changing isDeleted field to True
         */

        userRepository.deleteById(userId);
    }

}
