package com.example.IntegrationTest.Controller.BookController;

import com.example.model.Book;
import com.example.repository.BookRepository;
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
public class DeleteBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setup() {
        bookRepository.deleteAll();
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = new Book();
        book.setName("Effective Java");
        book.setAuthor("Nelofar Zabi");
        book.setDetails("Best practices for Java programming");
        Book savedBook = bookRepository.save(book);
        mockMvc.perform(delete("/api/books/{id}", savedBook.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        assertThat(bookRepository.findById(savedBook.getId())).isEmpty();
    }

    @Test
    public void testDeleteBookNotFound() throws Exception {
        mockMvc.perform(delete("/api/books/{id}", 999L).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }
}

