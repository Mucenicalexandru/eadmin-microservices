package com.eadmin.user.service.service;

import com.eadmin.user.service.model.User;
import com.eadmin.user.service.model.UserRole;
import com.eadmin.user.service.model.UserStatus;
import com.eadmin.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(){
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public ResponseEntity<?> addUser(UserRole userRole, User user) {
        HttpHeaders responseHeaders = new HttpHeaders();

        if (!userRepository.existsByEmail(user.getEmail())) {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);
            user.setRole(userRole);

            if (userRole == UserRole.USER) {
                user.setUserStatus(UserStatus.PENDING);
            } else {
                user.setUserStatus(UserStatus.ACCEPTED);
            }

            userRepository.save(user);
            return new ResponseEntity<>(responseHeaders, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(responseHeaders, HttpStatus.CONFLICT);
        }
    }

    public User getUserById(Long id){
        return userRepository.findUserByUserId(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
