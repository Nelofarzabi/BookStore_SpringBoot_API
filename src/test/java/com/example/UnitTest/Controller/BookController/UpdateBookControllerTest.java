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
public class UpdateBookControllerTest {

    @Mock
    private BookService bookService;

    private BookController bookController;

    @BeforeEach
    public void setup() {
        bookController = new BookController();
        bookController.setBookService(bookService);
    }

    @Test
    public void testUpdateBookSuccess() {
        Long bookId = 1L;
        Book bookDetails = new Book();
        bookDetails.setName("Updated Title");
        bookDetails.setAuthor("Updated Author");
        bookDetails.setDetails("Updated Details");


        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        updatedBook.setName("Updated Title");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setDetails("Updated Details");

        when(bookService.updateBook(eq(bookId), any(Book.class))).thenReturn(updatedBook);

        ResponseEntity<String> response = bookController.updateBook(bookId, bookDetails);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book successfully updated.", response.getBody());
        verify(bookService, times(1)).updateBook(eq(bookId), any(Book.class));
    }

    @Test
    public void testUpdateBookNotFound() {
        Long bookId = 2L;
        Book bookDetails = new Book();
        bookDetails.setName("No existent Book");

        when(bookService.updateBook(eq(bookId), any(Book.class))).thenReturn(null);
        ResponseEntity<String> response = bookController.updateBook(bookId, bookDetails);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed to update the book.", response.getBody());

        verify(bookService, times(1)).updateBook(eq(bookId), any(Book.class));
    }

    @Test
    public void testUpdateBookException() {
        Long bookId = 3L;
        Book bookDetails = new Book();
        bookDetails.setName("Error Book");
        when(bookService.updateBook(eq(bookId), any(Book.class))).thenThrow(new RuntimeException("Database error"));

        ResponseEntity<String> response = bookController.updateBook(bookId, bookDetails);
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("An error occurred: Database error"));
        verify(bookService, times(1)).updateBook(eq(bookId), any(Book.class));
    }
}

