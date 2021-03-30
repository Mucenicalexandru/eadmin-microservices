package com.eadmin.user.service.service;

import com.eadmin.user.service.VO.ResponseTemplateVO;
import com.eadmin.user.service.VO.Review;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

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

    public List<User> getAllUsersByGroupId(Long groupId){
        return userRepository.findAllByGroupId(groupId);
    }

    public List<User> getAllUsersByBuildingId(Long buildingId){
        return userRepository.findAllByBuildingId(buildingId);
    }

    public User getUserById(Long id){
        return userRepository.findUserByUserId(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getUsersByRoleAndTown(String role, String town){return userRepository.findAllByRolesAndTown(role, town);}

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

    public ResponseTemplateVO getProviderWithReviews(Long providerId){
        ResponseTemplateVO response = new ResponseTemplateVO();

        int totalStars = 0;
        HashMap<Integer, Integer> eachStarTotalNumber = new HashMap<>();
        eachStarTotalNumber.put(1, 0);
        eachStarTotalNumber.put(2, 0);
        eachStarTotalNumber.put(3, 0);
        eachStarTotalNumber.put(4, 0);
        eachStarTotalNumber.put(5, 0);


        User provider = userRepository.findUserByUserId(providerId);
        response.setUser(provider);

        Review [] reviews = restTemplate.getForObject("http://REVIEW-SERVICE/review/by-provider/" + providerId, Review[].class);

        assert reviews != null;
        response.setTotalReviews(reviews.length);

        for(Review review : reviews){
            totalStars += review.getStarNumber();
            if(review.getStarNumber() == 1){
                eachStarTotalNumber.put(1, eachStarTotalNumber.get(1) + 1);
            }else if(review.getStarNumber() == 2){
                eachStarTotalNumber.put(2, eachStarTotalNumber.get(2) + 1);
            }else if(review.getStarNumber() == 3){
                eachStarTotalNumber.put(3, eachStarTotalNumber.get(3) + 1);
            }else if(review.getStarNumber() == 4){
                eachStarTotalNumber.put(4, eachStarTotalNumber.get(4) + 1);
            }else if(review.getStarNumber() == 5){
                eachStarTotalNumber.put(5, eachStarTotalNumber.get(5) + 1);
            }
        }

        response.setStarStatistics(eachStarTotalNumber);

        if(reviews.length != 0){
            float reviewLength = (float) reviews.length;
            response.setAverageStars(totalStars / reviewLength);
        }


        return response;

    }

    public List<ResponseTemplateVO> getAllProvidersWithReviews(){

        List<ResponseTemplateVO> responseList = new ArrayList<>();

        List<User> providerList = userRepository.findAllByRoles("SERVICE_PROVIDER");

        for(User provider : providerList){
            ResponseTemplateVO response;
            response = getProviderWithReviews(provider.getUserId());
            responseList.add(response);

        }

        return responseList;

    }

    public List<ResponseTemplateVO> getProvidersWithReviewsByTown(String town){
        List<ResponseTemplateVO> responseList = new ArrayList<>();
        List<User> providerList = userRepository.findAllByRolesAndTown("SERVICE_PROVIDER", town);

        for(User provider : providerList){
            ResponseTemplateVO response;
            response = getProviderWithReviews(provider.getUserId());
            responseList.add(response);

        }

        return responseList;

    }

    public List<ResponseTemplateVO> getProvidersWithReviewsByTownAndByDepartment(String town, String department){
        List<ResponseTemplateVO> responseList = new ArrayList<>();
        List<User> providerList = userRepository.findAllByRolesAndTownAndDepartment("SERVICE_PROVIDER", town, department);

        for(User provider : providerList){
            ResponseTemplateVO response;
            response = getProviderWithReviews(provider.getUserId());
            responseList.add(response);

        }

        return responseList;

    }

    public void deleteUsersByGroupId(Long groupId){
        List<User> usersToDelete = userRepository.findAllByGroupId(groupId);

        for(User user : usersToDelete){
            userRepository.deleteById(user.getUserId());
        }
    }

    public void deleteUsersByBuildingId(Long buildingId){
        List<User> usersToDelete = userRepository.findAllByBuildingId(buildingId);

        for(User user : usersToDelete){
            userRepository.deleteById(user.getUserId());
        }
    }

}
