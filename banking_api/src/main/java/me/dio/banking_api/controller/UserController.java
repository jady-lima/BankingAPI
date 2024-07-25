package me.dio.banking_api.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.banking_api.domain.model.User;
import me.dio.banking_api.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "Operations pertaining to user in User Management System")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "View a list of available users")
    public ResponseEntity<List<User>> findAllUsers(){
        var users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search")
    @Operation(summary = "Search users by name or email")
    public ResponseEntity<List<User>> findUserByName(
        @RequestParam(value = "name", required = false, defaultValue = "") String name
    ){
        
        List<User> users = userService.findUserByName(name);

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by Id")
    public ResponseEntity<User> findUserByUserId(@PathVariable Long id){
        var user = userService.findUserByUserId(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        var userCreated = userService.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(userCreated.getId())
                        .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }

    @PostMapping("/createusers")
    @Operation(summary = "Create a list of users")
    public ResponseEntity<List<User>> createAllUsers(@RequestBody List<User> users){
        try {
            List<User> createdUsers = userService.createAllUsers(users);
            return ResponseEntity.ok(createdUsers);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // 400 Bad Request
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a user by Id")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newUserData) {
        try {
            User updatedUser = userService.updateUser(id, newUserData);
            return ResponseEntity.ok(updatedUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by Id")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
