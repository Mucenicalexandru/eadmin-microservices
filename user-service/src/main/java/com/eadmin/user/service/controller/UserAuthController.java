package com.eadmin.user.service.controller;

import com.eadmin.user.service.model.User;
import com.eadmin.user.service.model.UserStatus;
import com.eadmin.user.service.repository.UserRepository;
import com.eadmin.user.service.security.JwtTokenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserAuthController {

    @Autowired
    private UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenServices jwtTokenServices;

    public UserAuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, UserRepository users) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        if(userRepository.existsByEmail(user.getEmail())){
            User activeUser = userRepository.findByEmail(user.getEmail());

            if(activeUser.getRoles().contains("ADMIN")){
                String email = user.getEmail();
                Long id = userRepository.findByEmail(email).getUserId();
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, user.getPassword()));
                List<String> roles = authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());

                String token = jwtTokenServices.createAdminToken(email, roles, id);
                Map<Object, Object> model = new HashMap<>();
                model.put("email", email);
                model.put("roles", roles);
                model.put("token", token);

                return ResponseEntity.ok(model);
            }else if(activeUser.getRoles().contains("ADMINISTRATOR")){
                String email = user.getEmail();
                Long id = userRepository.findByEmail(email).getUserId();
                Long groupId = userRepository.findByEmail(email).getGroupId();

                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, user.getPassword()));
                List<String> roles = authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());

                String token = jwtTokenServices.createAdministratorToken(email, roles, id, groupId);
                Map<Object, Object> model = new HashMap<>();
                model.put("email", email);
                model.put("roles", roles);
                model.put("token", token);

                return ResponseEntity.ok(model);
            }else if(activeUser.getRoles().contains("PRESIDENT")) {
                String email = user.getEmail();
                Long id = userRepository.findByEmail(email).getUserId();
                Long buildingId = userRepository.findByEmail(email).getBuildingId();
                Long groupId = userRepository.findByEmail(email).getGroupId();
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, user.getPassword()));
                List<String> roles = authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());

                String token = jwtTokenServices.createToken(email, roles, id, buildingId, groupId);
                Map<Object, Object> model = new HashMap<>();
                model.put("email", email);
                model.put("roles", roles);
                model.put("token", token);

                return ResponseEntity.ok(model);
            }else if(activeUser.getRoles().contains("SERVICE_PROVIDER")) {
                String email = user.getEmail();
                Long id = userRepository.findByEmail(email).getUserId();
                String department = userRepository.findByEmail(email).getDepartment();
                String town = userRepository.findByEmail(email).getTown();
                // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, user.getPassword()));
                List<String> roles = authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList());


                String token = jwtTokenServices.createProviderToken(email, roles, id, department, town);

                Map<Object, Object> model = new HashMap<>();
                model.put("email", email);
                model.put("roles", roles);
                model.put("token", token);
                model.put("town", town);
                model.put("department", department);


                return ResponseEntity.ok(model);
            }else{
                if(activeUser.getUserStatus() == UserStatus.ACCEPTED){
                    String email = user.getEmail();
                    Long buildingId = userRepository.findByEmail(email).getBuildingId();
                    Long groupId = userRepository.findByEmail(email).getGroupId();
                    Long id = userRepository.findByEmail(email).getUserId();
                    // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
                    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, user.getPassword()));
                    List<String> roles = authentication.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList());


                    String token = jwtTokenServices.createToken(email, roles, id, buildingId, groupId);

                    Map<Object, Object> model = new HashMap<>();
                    model.put("email", email);
                    model.put("roles", roles);
                    model.put("token", token);

                    return ResponseEntity.ok(model);
                }else{
                    HttpHeaders responseHeaders = new HttpHeaders();
                    return new ResponseEntity<>(responseHeaders, HttpStatus.SERVICE_UNAVAILABLE);
                }
            }
        }else{
            HttpHeaders responseHeaders = new HttpHeaders();
            return new ResponseEntity<>(responseHeaders, HttpStatus.CONFLICT);
        }
    }

}
