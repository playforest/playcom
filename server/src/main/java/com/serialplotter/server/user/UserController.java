package com.serialplotter.server.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping()
    public User addNewUser(@RequestBody User user) {
        userService.insertUser(user);
        return user;
    }

    @PutMapping(path="/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user) {
        userService.updateUser(userId, user);
        return user;
    }

    @PatchMapping(path="/{userId}")
    public void partialUpdateUser(@PathVariable Long userId, @RequestBody Map<String, Object> updates) {
        userService.partialUpdateUser(userId, updates);
    }

    @DeleteMapping(path="/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.removeUser(userId);
    }
}
