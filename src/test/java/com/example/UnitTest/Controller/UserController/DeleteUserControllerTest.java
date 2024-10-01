package com.example.UnitTest.Controller.UserController;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
public class DeleteUserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void setup() {
        userController = new UserController();
        userController.setUserService(userService);
    }

    @Test
    public void testDeleteUserSuccess() {
        Long userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(true);
        ResponseEntity<String> response = userController.deleteUser(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User successfully deleted.", response.getBody());

        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    public void testDeleteUserUserNotFound() {
        Long userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(false);
        ResponseEntity<String> response = userController.deleteUser(userId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found. Deletion failed.", response.getBody());

        verify(userService, times(1)).deleteUser(userId);
    }
}


