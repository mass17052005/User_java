package com.example.project1;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/users")
public class userController {

    private List<user> users = new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong(1);

    @PostMapping
    public user addUser(@RequestBody user user) {
        user.setId(idCounter.getAndIncrement());
        users.add(user);
        return user;
    }

    @GetMapping
    public List<user> getAllUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public user getUserById(@PathVariable Long id) {
        for (user u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        throw new RuntimeException("User not found with id: " + id);
    }
}
