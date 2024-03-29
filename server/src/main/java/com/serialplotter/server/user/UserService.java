package com.serialplotter.server.user;

import com.serialplotter.server.registration.EmailValidator;
import com.serialplotter.server.registration.token.ConfirmationToken;
import com.serialplotter.server.registration.token.ConfirmationTokenService;
import jakarta.transaction.Transactional;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /*
    TODO:
    - send back meaningful response payloads
     */

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailValidator emailValidator;
    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found";
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ConfirmationTokenService confirmationTokenService, EmailValidator emailValidator) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.emailValidator = emailValidator;
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
    public User loadUserByUsername(String identifier) throws UsernameNotFoundException {

        if(emailValidator.test(identifier)) {
            return userRepository.findUserByEmail(identifier)
                    .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, identifier)));
        } else {
            return userRepository.findUserByUsername(identifier)
                    .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, identifier)));
        }

    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository
                .findUserByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            // TODO: check of attributes are the same
            // and if not confirmed send confirmation email
            // test case: we force expire token and then try
            // to sign up with the same email, this triggers
            // an 'email already taken' exception

            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.println(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
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

            if (updatedUser.getUsername().length() > 3) {
                user.setUsername(updatedUser.getUsername());
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

    public void enableUser(Long id) {
        userRepository.setUserEnabled(id);
    }


}
