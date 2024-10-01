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
public class UpdateUserTest {

    @Mock
    private UserService userService;

    @Mock
    private User existingUser;
    private User updatedDetails;

    @BeforeEach
    public void setup() {
        existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Nelofar");
        existingUser.setLastname("Zabi");
        existingUser.setGender("Famale");
        existingUser.setAddress("USA");
        existingUser.setEmail("nelofarzabi@gmail.com");

        updatedDetails = new User();
        updatedDetails.setName("Zuhra");
        updatedDetails.setLastname("Hashimi");
        updatedDetails.setGender("Female");
        updatedDetails.setAddress("Uk");
        updatedDetails.setEmail("zuhrahashimi@gmail.com");
    }

    @Test
    public void testUpdateUserExists() {
        when(userService.updateUser(existingUser.getId(), updatedDetails)).thenReturn(updatedDetails);

        User result = userService.updateUser(1L, updatedDetails);
        assertNotNull(result);
        assertEquals(result.getName(), "Zuhra");
        assertEquals(result.getLastname(), "Hashimi");
        assertEquals(result.getGender(), "Female");
        assertEquals(result.getAddress(), "Uk");
        assertEquals(result.getEmail(), "zuhrahashimi@gmail.com");

        verify(userService, times(1)).updateUser(existingUser.getId(), updatedDetails);
    }

    @Test
    public void testUpdateUserDoesNotExist() {
        when(userService.updateUser(existingUser.getId(), updatedDetails)).thenReturn(null);
        User result = userService.updateUser(1L, updatedDetails);
        assertNull(result);
        verify(userService, never()).createUser(any(User.class));
    }
}

