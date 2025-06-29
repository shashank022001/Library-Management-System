package com.LibraryManagementSystem.controller;

import com.LibraryManagementSystem.dto.UserDTO;
import com.LibraryManagementSystem.serviceinterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Integer userId, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userId, userDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok(userService.signIn(username, password));
    }

    @PostMapping("/signout")
    public ResponseEntity<String> signOut(@RequestParam String username) {
        return ResponseEntity.ok(userService.signOut(username));
    }
}
