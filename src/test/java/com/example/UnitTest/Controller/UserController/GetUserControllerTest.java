package com.example.UnitTest.Controller.UserController;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.model.User;
import com.example.service.UserService;
import com.example.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetUserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void setup() {
        userController = new UserController();
        userController.setUserService(userService);
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Nelofar Zabi");
        user1.setLastname("Zabi");
        user1.setGender("Female");
        user1.setAddress("Kabul, Afghanistan");
        user1.setEmail("nelofarzabi@gmail.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Zuhra");
        user2.setLastname("Hashimi");
        user2.setGender("Female");
        user2.setAddress("New York, USA");
        user2.setEmail("zuhrahashimi@gmail.com");

        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        List<User> result = userController.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());


        assertEquals("Nelofar Zabi", result.get(0).getName());
        assertEquals("Zabi", result.get(0).getLastname());
        assertEquals("Female", result.get(0).getGender());
        assertEquals("Kabul, Afghanistan", result.get(0).getAddress());
        assertEquals("nelofarzabi@gmail.com", result.get(0).getEmail());

        assertEquals("Zuhra", result.get(1).getName());
        assertEquals("Hashimi", result.get(1).getLastname());
        assertEquals("Female", result.get(1).getGender());
        assertEquals("New York, USA", result.get(1).getAddress());
        assertEquals("zuhrahashimi@gmail.com", result.get(1).getEmail());

        verify(userService, times(1)).getAllUsers();
    }
}
