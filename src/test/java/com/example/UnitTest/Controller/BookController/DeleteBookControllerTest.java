package com.example.UnitTest.Controller.BookController;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
public class DeleteBookControllerTest {

    @Mock
    private BookService bookService;

    private BookController bookController;

    @BeforeEach
    public void setup() {
        bookController = new BookController();
        bookController.setBookService(bookService);
    }

    @Test
    public void testDeleteBookSuccess() {
        Long bookId = 1L;
        when(bookService.deleteBook(bookId)).thenReturn(true);

        ResponseEntity<String> response = bookController.deleteBook(bookId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book successfully deleted.", response.getBody());
        verify(bookService, times(1)).deleteBook(bookId);
    }

    @Test
    public void testDeleteBookNotFound() {
        Long bookId = 2L;
        when(bookService.deleteBook(bookId)).thenReturn(false);
        ResponseEntity<String> response = bookController.deleteBook(bookId);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed to delete the book.", response.getBody());
        verify(bookService, times(1)).deleteBook(bookId);
    }

    @Test
    public void testDeleteBookException() {
        Long bookId = 3L;

        when(bookService.deleteBook(bookId)).thenThrow(new RuntimeException("Database error"));
        ResponseEntity<String> response = bookController.deleteBook(bookId);
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().contains("An error occurred: Database error"));

        verify(bookService, times(1)).deleteBook(bookId);
    }
}

