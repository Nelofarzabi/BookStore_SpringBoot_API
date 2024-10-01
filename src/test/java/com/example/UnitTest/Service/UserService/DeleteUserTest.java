package com.example.UnitTest.Service.UserService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DeleteUserTest {

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeleteUserExists() {
        Long userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(true);
        boolean result = userService.deleteUser(userId);
        verify(userService).deleteUser(userId);
        assertTrue(result);
    }

    @Test
    public void testDeleteUserDoesNotExist() {
        Long userId = 1L;
        when(userService.deleteUser(userId)).thenReturn(false);
        boolean result = userService.deleteUser(userId);
        verify(userService).deleteUser(userId);
        assertFalse(result);
    }
}
