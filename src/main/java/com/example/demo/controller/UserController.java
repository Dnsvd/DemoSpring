package com.example.demo.controller;

import com.example.demo.repository.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
       return userService.findAll();
    }
    @PostMapping
        public User create(@RequestBody User user) {
        return userService.create(user);
//        if (!userService.isEmailUnique(user.getEmail())) {
//            throw new IllegalArgumentException("Email address already in use");
//        }
//        return user;
    }
    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(name = "id") long id) {
        userService.delete(id);
    }
    @PutMapping(path = "{id}")
    public void update(
            @PathVariable long id,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name) {
        userService.update(id, email, name);
    }
}
