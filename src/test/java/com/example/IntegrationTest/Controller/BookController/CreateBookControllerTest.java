package com.example.IntegrationTest.Controller.BookController;

import com.example.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateBookSuccess() throws Exception {
        Book book = new Book();
        book.setName("Effective Java");
        book.setAuthor("Nelofar Zabi");
        book.setDetails("Best practices for Java programming");

        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(book))).andExpect(status().isOk()).andExpect(content().string("Book successfully created."));
    }
}

