package com.serialplotter.server.user;

import jakarta.transaction.Transactional;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    /*
    TODO:
    - send back meaningful response payloads

     */

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
        boolean userExists = userRepository.existsById(id);

        if (!userExists) {
            throw new IllegalArgumentException("User ID [" + id + "] does not exist");
        }


        userRepository.updateUserById(id, user.getName(), user.getEmail(), user.getRole(),
                                       user.getLastlogin(), LocalDateTime.now(), user.getStatus());
    }

    @Transactional
    public void partialUpdateUser(Long id, Map<String, Object> updates) {
        Optional<User> userById = userRepository.findUserById(id);

        if(userById.isEmpty()) {
            throw new IllegalArgumentException("User ID [" + id + "] does not exist");
        } else {
            User user = userById.get();

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

    public void removeUser(Long id) {
        boolean userExists = userRepository.existsById(id);

        if (!userExists) {
            throw new IllegalArgumentException("User ID [" + id + "] does not exist");
        }
        /*
        TODO:
        - change function from deleting user from db to changing isDeleted field to True
         */

        userRepository.deleteById(id);
    }
}
