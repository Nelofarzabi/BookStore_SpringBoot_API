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

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(BookController.class)
public class GetAllBookControllerTest {

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
    public void testGetAllBooks() throws Exception {
        Book book1 = new Book(1L, "Effective Java", "Joshua Bloch", "Best practices for Java programming");
        Book book2 = new Book(2L, "Clean Code", "Robert C. Martin", "A Handbook of Agile Software Craftsmanship");

        List<Book> books = Arrays.asList(book1, book2);

        given(bookService.getAllBooks()).willReturn(books);
        mockMvc.perform(get("/api/books").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Effective Java"))
                .andExpect(jsonPath("$[1].name").value("Clean Code"));
    }

}

