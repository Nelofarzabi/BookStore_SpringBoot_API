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
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class CreateUserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void setup() {
        userController = new UserController();
        userController.setUserService(userService);
    }

    @Test
    public void testCreateUserSuccess() {

        User user = new User();
        user.setName("Nelofar Zabi");
        user.setLastname("Zabi");
        user.setGender("Female");
        user.setAddress("Kabul, Afghanistan");
        user.setEmail("nelofarzabi@gmail.com");

        when(userService.createUser(user)).thenReturn(user);

        ResponseEntity<String> response = userController.createUser(user);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User successfully created", response.getBody());

        verify(userService, times(1)).createUser(user);
    }

    @Test
    public void testCreateUserFailure() {

        User user = new User();
        user.setName("Invalid User");
        when(userService.createUser(user)).thenReturn(null);

        ResponseEntity<String> response = userController.createUser(user);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("User not created", response.getBody());

        verify(userService, times(1)).createUser(user);
    }

    @Test
    public void testCreateUserException() {
        User user = new User();
        user.setName("Nelofar");

        when(userService.createUser(user)).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<String> response = userController.createUser(user);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("An error occurred: Database error"));

        verify(userService, times(1)).createUser(user);
    }
}


