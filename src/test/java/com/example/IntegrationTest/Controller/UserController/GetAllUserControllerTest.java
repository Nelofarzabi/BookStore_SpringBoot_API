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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GetAllUserControllerTest {

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
    public void testGetAllUsers() throws Exception {
        User user = new User();
        user.setName("Nelofar");
        user.setLastname("Zabi");
        user.setEmail("nelofarzabi@gmail.com");
        user.setGender("Famale");
        user.setAddress("kabul");

        userRepository.save(user);

        MvcResult result = mockMvc.perform(get("/api/users").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        String responseContent = result.getResponse().getContentAsString();
        List<User> users = objectMapper.readValue(responseContent, objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));

        assertThat(users).hasSize(1);

        User savedUser = users.get(0);
        assertThat(savedUser.getName()).isEqualTo("Nelofar");
        assertThat(savedUser.getLastname()).isEqualTo("Zabi");
        assertThat(savedUser.getEmail()).isEqualTo("nelofarzabi@gmail.com");
        assertThat(savedUser.getAddress()).isEqualTo("kabul");

    }
}

