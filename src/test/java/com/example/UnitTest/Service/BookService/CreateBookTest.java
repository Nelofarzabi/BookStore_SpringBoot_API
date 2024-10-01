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
public class CreateBookTest {

    @Mock
    private BookService bookService;

    @Mock
    private Book book;

    @BeforeEach
    public void setup(){
        book = new Book();
        book.setId(1L);
        book.setName("Test Book");
        book.setAuthor("Test Author");
    }

    @Test
    public void testCreateBook() {
        when(bookService.createBook(book)).thenReturn(book);
        Book createdBook = bookService.createBook(book);
        assertNotNull(createdBook);
        assertEquals(createdBook.getId(), Long.valueOf(1));
        assertEquals(createdBook.getName(), "Test Book");
        assertEquals(createdBook.getAuthor(), "Test Author");
        verify(bookService, times(1)).createBook(book);
    }
}
