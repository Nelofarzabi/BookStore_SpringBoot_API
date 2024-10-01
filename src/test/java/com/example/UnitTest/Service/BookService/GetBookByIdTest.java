package com.example.UnitTest.Service.BookService;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

import com.example.model.Book;
import com.example.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class GetBookByIdTest {

    @Mock
    private BookService bookService;

    @Mock
    private Book book;

    @BeforeEach
    public void setup() {
        book = new Book();
        book.setId(1L);
        book.setName("The Great Gatsby");
        book.setAuthor("Nelofar Zabi");
        book.setDetails("This book was written by Nelofar Zabi.");
    }

    @Test
    public void testGetBookByIdSuccess() {
        when(bookService.getBookById(1L)).thenReturn(book);
        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals(result.getId(), 1L);
        assertEquals(result.getName(), "The Great Gatsby");
        assertEquals(result.getAuthor(), "Nelofar Zabi");
        assertEquals(result.getDetails(), "This book was written by Nelofar Zabi.");

        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    public void testGetBookByIdNotFound() {
        when(bookService.getBookById(2L)).thenReturn(null);
        Book result = bookService.getBookById(2L);
        assertNull(result);
        verify(bookService, times(1)).getBookById(2L);
    }
}

