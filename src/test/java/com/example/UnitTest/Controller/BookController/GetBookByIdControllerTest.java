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
public class GetBookByIdControllerTest {

    @Mock
    private BookService bookService;

    private BookController bookController;

    @BeforeEach
    public void setup() {
        bookController = new BookController();
        bookController.setBookService(bookService);
    }

    @Test
    public void testGetBookByIdSuccess() {
        Book book = new Book();
        book.setId(1L);
        book.setName("Sample Book");
        book.setAuthor("Nelofar Zabi");
        book.setDetails("This is the book");

        when(bookService.getBookById(1L)).thenReturn(book);

        ResponseEntity<Book> response = bookController.getBookById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Sample Book", response.getBody().getName());
        assertEquals("Nelofar Zabi", response.getBody().getAuthor());
        assertEquals("This is the book", response.getBody().getDetails());

        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    public void testGetBookByIdNotFound() {
        when(bookService.getBookById(2L)).thenReturn(null);

        ResponseEntity<Book> response = bookController.getBookById(2L);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(bookService, times(1)).getBookById(2L);
    }
}

