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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetAllUserTest {

    @Mock
    private UserService userService;

    @Mock
    private List<User> userList;

    @BeforeEach
    public void setup() {
        userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Nelofar Zabi");
        user1.setEmail("nelofarzabi@gmail.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("Zuhra Hashimi");
        user2.setEmail("zuhrahashmi@gmail.com");

        userList.add(user1);
        userList.add(user2);
    }

    @Test
    public void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(userList);
        List<User> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getName(), "Nelofar Zabi");
        assertEquals(result.get(1).getName(), "Zuhra Hashimi");

        verify(userService, times(1)).getAllUsers();
    }
}

