package com.example.IntegrationTest.Controller.BookController;

import com.example.controller.BookController;
import com.example.model.Book;
import com.example.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
public class GetBookByIdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetBookById() throws Exception {
        Long bookId = 1L;
        Book book = new Book(bookId, "Effective Java", "Joshua Bloch", "Best practices for Java programming");
        given(bookService.getBookById(bookId)).willReturn(book);
        mockMvc.perform(get("/api/books/{id}", bookId).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Effective Java")).andExpect(jsonPath("$.author").value("Joshua Bloch"));
    }
}

