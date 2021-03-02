package com.eadmin.user.service.controller;

import com.eadmin.user.service.model.User;
import com.eadmin.user.service.model.UserRole;
import com.eadmin.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/all")
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return userService.addUser(UserRole.USER, user);
    }

    @PostMapping("/president")
    public ResponseEntity<?> addPresident(@RequestBody User user){
        return userService.addUser(UserRole.PRESIDENT, user);
    }

    @PostMapping("/administrator")
    public ResponseEntity<?> addAdministrator(@RequestBody User user){
        return userService.addUser(UserRole.ADMINISTRATOR, user);
    }

    @PostMapping("/censor")
    public ResponseEntity<?> addCensor(@RequestBody User user){
        return userService.addUser(UserRole.CENSOR, user);
    }

    @PostMapping("/service-provider")
    public ResponseEntity<?> addServiceProvider(@RequestBody User user){
        return userService.addUser(UserRole.SERVICE_PROVIDER, user);
    }
}
