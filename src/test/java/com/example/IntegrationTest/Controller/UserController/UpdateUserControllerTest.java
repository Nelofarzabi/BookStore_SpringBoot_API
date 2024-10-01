package com.example.IntegrationTest.Controller.UserController;

import com.example.model.User;
import com.example.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        user.setName("Nelofar");
        user.setLastname("Zabi");
        user.setEmail("nelofarzabi@gmail.com");
        user.setGender("Female");
        user.setAddress("Kabul");

        User savedUser = userRepository.save(user);

        User updatedUserData = new User();
        updatedUserData.setName("Nelofar");
        updatedUserData.setLastname("Zabi");
        updatedUserData.setEmail("nelofarzabi@gmail.com");
        updatedUserData.setGender("Female");
        updatedUserData.setAddress("USA");
        MvcResult result = mockMvc.perform(put("/api/users/{id}", savedUser.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedUserData))).andExpect(status().isOk()).andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).isEqualTo("User successfully updated");
    }

    @Test
    public void testUpdateUserNotFound() throws Exception {
        User updatedUserData = new User();
        updatedUserData.setName("Nelofar");
        updatedUserData.setLastname("Zabi");
        updatedUserData.setEmail("nelofarzabi@gmail.com");
        updatedUserData.setGender("Female");
        updatedUserData.setAddress("USA");

        mockMvc.perform(put("/api/users/{id}", 999).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(updatedUserData))).andExpect(status().isNotFound());
    }
}

