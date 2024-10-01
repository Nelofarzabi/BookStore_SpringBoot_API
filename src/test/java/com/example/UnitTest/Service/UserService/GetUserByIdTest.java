package com.example.UnitTest.Service.UserService;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import com.example.model.User;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetUserByIdTest {

    @Mock
    private UserService userService;

    @Mock
    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setId(1L);
        user.setName("Nelofar Zabi");
        user.setEmail("nelofarzabi@gmail.com");
    }

    @Test
    public void testGetUserByIdUserFound() {
        when(userService.getUserById(1L)).thenReturn(user);

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(result.getId(), Long.valueOf(1));
        assertEquals(result.getName(), "Nelofar Zabi");
        assertEquals(result.getEmail(), "nelofarzabi@gmail.com");
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    public void testGetUserByIdUserNotFound() {
        when(userService.getUserById(2L)).thenReturn(null);
        User result = userService.getUserById(2L);
        assertNull(result);
        verify(userService, times(1)).getUserById(2L);
    }
}

