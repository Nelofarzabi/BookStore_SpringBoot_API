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
public class CreateUserTest {

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
    public void testCreateUser() {
        when(userService.createUser(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals(createdUser.getId(), Long.valueOf(1));
        assertEquals(createdUser.getName(), "Nelofar Zabi");
        assertEquals(createdUser.getEmail(), "nelofarzabi@gmail.com");

        verify(userService, times(1)).createUser(user);
    }
}

