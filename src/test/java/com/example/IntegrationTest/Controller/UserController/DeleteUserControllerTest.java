package com.example.IntegrationTest.Controller.UserController;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DeleteUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = new User();
        user.setName("Nelofar");
        user.setLastname("Zabi");
        user.setEmail("nelofarzabi@gmail.com");
        user.setGender("Female");
        user.setAddress("Kabul");

        User savedUser = userRepository.save(user);
        mockMvc.perform(delete("/api/users/{id}", savedUser.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        assertThat(userRepository.findById(savedUser.getId())).isEmpty();
    }

    @Test
    public void testDeleteUserNotFound() throws Exception {
        mockMvc.perform(delete("/api/users/{id}", 999).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }
}

