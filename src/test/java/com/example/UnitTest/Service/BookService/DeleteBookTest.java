package com.example.UnitTest.Service.BookService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DeleteBookTest {

    @Mock
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteBookSuccess() {
        Long bookId = 1L;
        when(bookService.deleteBook(bookId)).thenReturn(true);
        boolean result = bookService.deleteBook(bookId);

        verify(bookService).deleteBook(bookId);
        assertTrue(result);
    }

    @Test
    void testDeleteBookNotFound() {
        Long bookId = 1L;
        when(bookService.deleteBook(bookId)).thenReturn(false);
        boolean result = bookService.deleteBook(bookId);

        verify(bookService, never());
        assertFalse(result);
    }
}

