package com.eadmin.user.service.service;

import com.eadmin.user.service.model.User;
import com.eadmin.user.service.model.UserStatus;
import com.eadmin.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(){
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public ResponseEntity<?> addUser(String userRole, User user) {
        HttpHeaders responseHeaders = new HttpHeaders();

        if (!userRepository.existsByEmail(user.getEmail())) {
            String password = passwordEncoder.encode(user.getPassword());
            user.setPassword(password);


            if (userRole.equals("USER")) {
                user.setUserStatus(UserStatus.PENDING);
                user.getRoles().add(userRole);
            } else {
                user.setUserStatus(UserStatus.ACCEPTED);
                user.getRoles().add(userRole);
            }

            userRepository.save(user);
            return new ResponseEntity<>(responseHeaders, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(responseHeaders, HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> addWorkingMembers(String userRole, User user){
        String password = passwordEncoder.encode("1111");
        user.setPassword(password);
        user.setUserStatus(UserStatus.ACCEPTED);
        user.getRoles().add(userRole);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }


    public User getUserById(Long id){
        return userRepository.findUserByUserId(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getUsersByTown(String town){return userRepository.findAllByRolesAndTown("USER", town);}

    public List<User> getUsersByRoles(String role){return userRepository.findAllByRoles(role);}

    public User getUserByGroupAndRole(Long groupId, String role){
        return userRepository.findByGroupIdAndRoles(groupId, role);
    }

    public User getUserByBuildingIdAndRole(Long buildingId, String role){
        return userRepository.findByBuildingIdAndRoles(buildingId, role);
    }

    public ResponseEntity<User> saveUser(User user){
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public List<User> getPendingUsersByGroupId(Long groupId, UserStatus userStatus){
        return userRepository.findAllByGroupIdAndUserStatus(groupId, userStatus);
    }

}
