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
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class GetUserByIdControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void setup() {
        userController = new UserController();
        userController.setUserService(userService);
    }

    @Test
    public void testGetUserByIdUserFound() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("Nelofar");
        user.setLastname("Zabi");
        user.setGender("Female");
        user.setAddress("Kabul, Afghanistan");
        user.setEmail("nelofarzabi@gmail.com");

        when(userService.getUserById(userId)).thenReturn(user);
        ResponseEntity<User> response = userController.getUserById(userId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());

        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testGetUserByIdUserNotFound() {
        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(null);
        ResponseEntity<User> response = userController.getUserById(userId);

        assertNotNull(response);
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());

        verify(userService, times(1)).getUserById(userId);
    }
}

