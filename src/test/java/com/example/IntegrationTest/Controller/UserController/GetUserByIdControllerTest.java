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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetUserByIdControllerTest {

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
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setName("Nelofar");
        user.setLastname("Zabi");
        user.setEmail("nelofarzabi@gmail.com");
        user.setGender("Famale");
        user.setAddress("Kabul");

        User savedUser = userRepository.save(user);

        MvcResult result = mockMvc.perform(get("/api/users/{id}", savedUser.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String responseContent = result.getResponse().getContentAsString();
        User fetchedUser = objectMapper.readValue(responseContent, User.class);

        assertThat(fetchedUser).isNotNull();
        assertThat(fetchedUser.getId()).isEqualTo(savedUser.getId());
        assertThat(fetchedUser.getName()).isEqualTo(savedUser.getName());
        assertThat(fetchedUser.getLastname()).isEqualTo(savedUser.getLastname());
        assertThat(fetchedUser.getEmail()).isEqualTo(savedUser.getEmail());
    }

    @Test
    public void testGetUserByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/users/{id}", 999).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }
}

