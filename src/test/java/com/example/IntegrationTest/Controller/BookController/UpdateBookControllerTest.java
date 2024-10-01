package com.example.IntegrationTest.Controller.BookController;

import com.example.model.Book;
import com.example.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        bookRepository.deleteAll();
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book();
        book.setName("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setDetails("Best practices for Java programming");

        Book savedBook = bookRepository.save(book);

        savedBook.setName("Effective Java - Updated");
        savedBook.setDetails("Updated description for best practices in Java programming");

        String updatedBookJson = objectMapper.writeValueAsString(savedBook);

        mockMvc.perform(put("/api/books/{id}", savedBook.getId()).contentType(MediaType.APPLICATION_JSON).content(updatedBookJson)).andExpect(status().isOk());
        Optional<Book> updatedBook = bookRepository.findById(savedBook.getId());
        assertThat(updatedBook).isPresent();
        assertThat(updatedBook.get().getName()).isEqualTo("Effective Java - Updated");
        assertThat(updatedBook.get().getDetails()).isEqualTo("Updated description for best practices in Java programming");
    }

    @Test
    public void testUpdateBookNotFound() throws Exception {
        Book book = new Book();
        book.setId(999L);
        book.setName("Non-existent Book");
        book.setAuthor("Unknown Author");
        book.setDetails("This book does not exist in the database");
        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(put("/api/books/{id}", 999L).contentType(MediaType.APPLICATION_JSON).content(bookJson)).andExpect(status().isNotFound());
    }
}

