package com.example.UnitTest.Controller.BookController;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.model.Book;
import com.example.service.BookService;
import com.example.controller.BookController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class CreateBookControllerTest {

    @Mock
    private BookService bookService;

    private BookController bookController;

    @BeforeEach
    public void setup() {
        bookController = new BookController();
        bookController.setBookService(bookService);
    }

    @Test
    public void testCreateBookSuccess() {
        Book book = new Book();
        book.setName("The river");
        book.setAuthor("Nelofar zabi");
        book.setDetails("This the book by Nelofar zabi");

        when(bookService.createBook(book)).thenReturn(book);

        ResponseEntity<String> response = bookController.createBook(book);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book successfully created.", response.getBody());

        verify(bookService, times(1)).createBook(book);
    }

    @Test
    public void testCreateBookFailure() {
        Book book = new Book();
        book.setName("Invalid Book");

        when(bookService.createBook(book)).thenReturn(null);
        ResponseEntity<String> response = bookController.createBook(book);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Book creation failed.", response.getBody());

        verify(bookService, times(1)).createBook(book);
    }

    @Test
    public void testCreateBookException() {
        Book book = new Book();
        book.setName("Book With Error");
        when(bookService.createBook(book)).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<String> response = bookController.createBook(book);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("An error occurred: Database error"));

        verify(bookService, times(1)).createBook(book);
    }
}

