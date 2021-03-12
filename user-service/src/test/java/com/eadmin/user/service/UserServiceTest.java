package com.eadmin.user.service;

import com.eadmin.user.service.model.User;
import com.eadmin.user.service.repository.UserRepository;
import com.eadmin.user.service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private final User user1 = new User();
    private final User user2 = new User();

    @BeforeEach
    void setup(){
        user1.setLastName("Doe");
        user1.setFirstName("John");

        user2.setFirstName("Second");
        user2.setLastName("User");
    }

    @Test
    public void getAllUsers(){
        when(userRepository.findAll()).thenReturn(Stream
        .of(user1, user2).collect(Collectors.toList()));

        assertEquals(2, userService.getAllUsers().size());
    }



}
