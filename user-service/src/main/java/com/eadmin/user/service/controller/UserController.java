package com.eadmin.user.service.controller;

import com.eadmin.user.service.VO.ResponseTemplateVO;
import com.eadmin.user.service.model.User;
import com.eadmin.user.service.model.UserStatus;
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

    @GetMapping("/all-by-role/{role}")
    private List<User> getAllUsersByRole(@PathVariable String role){
        return userService.getUsersByRoles(role);
    }

    @GetMapping("/all-by-town/{town}")
    public List<User> getUsersByTown(@PathVariable String town){
        return userService.getUsersByRoleAndTown("USER", town);
    }

    @GetMapping("/providers-by/{town}")
    public List<User> getProvidersByTown(@PathVariable String town){
        return userService.getUsersByRoleAndTown("SERVICE_PROVIDER", town);
    }

    @GetMapping("/providers-by/{town}/{department}")
    public List<User> getProvidersByTown(@PathVariable String town, @PathVariable String department){
        return userService.getProvidersByTownAndDepartment(town, department);
    }

    @GetMapping("/by-group-and-role/{groupId}/{role}")
    public User getUserByGroupAndRole(@PathVariable Long groupId, @PathVariable String role){
        return userService.getUserByGroupAndRole(groupId, role);
    }

    @GetMapping("/by-buildingId-and-role/{buildingId}/{role}")
    public User getUserByBuildingIdAndRole(@PathVariable Long buildingId, @PathVariable String role){
        return userService.getUserByBuildingIdAndRole(buildingId, role);
    }

    @GetMapping("/pending/{groupId}")
    public List<User> getPendingUsers(@PathVariable Long groupId){
        return userService.getPendingUsersByGroupId(groupId, UserStatus.PENDING);
    }

    @GetMapping("/provider-with-reviews/{providerId}")
    public ResponseTemplateVO getProviderWithReviews(@PathVariable Long providerId){
        return userService.getProviderWithReviews(providerId);
    }

    @PostMapping("/")
    public void addUser(@RequestBody User user){
         userService.addUser("USER", user);
    }

    @PostMapping("/admin")
    public void addAdmin(@RequestBody User user){
        userService.addUser("ADMIN", user);
    }

    @PostMapping("/add-president")
    public ResponseEntity<?> addPresident(@RequestBody User user){
        return userService.addWorkingMembers("PRESIDENT", user);
    }

    @PostMapping("/add-administrator")
    public ResponseEntity<?> addAdministrator(@RequestBody User user){
        return userService.addWorkingMembers("ADMINISTRATOR", user);
    }

    @PostMapping("/add-censor")
    public ResponseEntity<?> addCensor(@RequestBody User user){
        return userService.addWorkingMembers("CENSOR", user);
    }

    @PostMapping("/add-service-provider")
    public ResponseEntity<?> addServiceProvider(@RequestBody User user){
        return userService.addUser("SERVICE_PROVIDER", user);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<User> editUser(@PathVariable Long userId, @RequestBody User user){
        User userToUpdate = userService.getUserById(userId);

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setPhone(user.getPhone());

        return userService.saveUser(userToUpdate);
    }

    @PutMapping("/accept-request/{userId}")
    public ResponseEntity<User> acceptUser(@PathVariable Long userId){
        User userToUpdate = userService.getUserById(userId);

        userToUpdate.setUserStatus(UserStatus.ACCEPTED);

        return userService.saveUser(userToUpdate);
    }

    @PutMapping("/reject-request/{userId}")
    public ResponseEntity<User> rejectUser(@PathVariable Long userId){
        User userToUpdate = userService.getUserById(userId);

        userToUpdate.setUserStatus(UserStatus.REJECTED);

        return userService.saveUser(userToUpdate);
    }
}
