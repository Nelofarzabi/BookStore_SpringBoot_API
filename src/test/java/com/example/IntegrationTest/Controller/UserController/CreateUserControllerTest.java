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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateUserControllerTest {

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
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setName("Nelofar");
        user.setLastname("Zabi");
        user.setEmail("nelofarzabi@gmail.com");
        user.setGender("Famale");
        user.setAddress("Kabul");

        MvcResult result = mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user))).andExpect(status().isOk()).andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertThat(responseContent).isEqualTo("User successfully created");

        assertThat(userRepository.findAll()).hasSize(1);
        User savedUser = userRepository.findAll().get(0);
        assertThat(savedUser.getName()).isEqualTo("Nelofar");
        assertThat(savedUser.getLastname()).isEqualTo("Zabi");
        assertThat(savedUser.getEmail()).isEqualTo("nelofarzabi@gmail.com");
        assertThat(savedUser.getGender()).isEqualTo("Famale");
        assertThat(savedUser.getAddress()).isEqualTo("Kabul");
    }
}

