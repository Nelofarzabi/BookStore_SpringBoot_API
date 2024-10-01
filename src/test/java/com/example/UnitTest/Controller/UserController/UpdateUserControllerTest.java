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
public class UpdateUserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void setup() {
        userController = new UserController();
        userController.setUserService(userService);
    }

    @Test
    public void testUpdateUserSuccess() {
        Long userId = 1L;
        User userDetails = new User();
        userDetails.setName("Nelofar");
        userDetails.setLastname("Zabi");
        userDetails.setGender("Female");
        userDetails.setAddress("Kabul, Afghanistan");
        userDetails.setEmail("nelofarzabi@gmail.com");

        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setName("Zuhra");
        updatedUser.setLastname("Hashimi");
        updatedUser.setGender("Female");
        updatedUser.setAddress("United of Afghanistan");
        updatedUser.setEmail("zuhraHashimi@gmail.com");

        when(userService.updateUser(userId, userDetails)).thenReturn(updatedUser);

        ResponseEntity<String> response = userController.updateUser(userId, userDetails);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User successfully updated", response.getBody());

        verify(userService, times(1)).updateUser(userId, userDetails);
    }

    @Test
    public void testUpdateUserUserNotFound() {
        Long userId = 1L;
        User userDetails = new User();
        userDetails.setName("Invalid User");

        when(userService.updateUser(userId, userDetails)).thenReturn(null);

        ResponseEntity<String> response = userController.updateUser(userId, userDetails);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());

        verify(userService, times(1)).updateUser(userId, userDetails);
    }

    @Test
    public void testUpdateUserException() {
        Long userId = 1L;
        User userDetails = new User();
        userDetails.setName("Nelofar");

        when(userService.updateUser(userId, userDetails)).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<String> response = userController.updateUser(userId, userDetails);
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("An error occurred: Database error"));

        verify(userService, times(1)).updateUser(userId, userDetails);
    }
}

