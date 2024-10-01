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

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetBookControllerTest {

    @Mock
    private BookService bookService;

    private BookController bookController;

    @BeforeEach
    public void setup() {
        bookController = new BookController();
        bookController.setBookService(bookService);
    }

    @Test
    public void testGetAllBooksSuccess() {

        Book book1 = new Book();
        book1.setName("The camera");
        book1.setAuthor("Sara Zabi");
        book1.setDetails("This is the details");

        List<Book> books = List.of(book1);

        when(bookService.getAllBooks()).thenReturn(books);
        List<Book> result = bookController.getAllBooks();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("The camera", result.get(0).getName());
        assertEquals("Sara Zabi", result.get(0).getAuthor());
        assertEquals("This is the details", result.get(0).getDetails());

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    public void testGetAllBooksEmptyList() {
        List<Book> emptyBooks = List.of();

        when(bookService.getAllBooks()).thenReturn(emptyBooks);
        List<Book> result = bookController.getAllBooks();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(bookService, times(1)).getAllBooks();
    }
}

