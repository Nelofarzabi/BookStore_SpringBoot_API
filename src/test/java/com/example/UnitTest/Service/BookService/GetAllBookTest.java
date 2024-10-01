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

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetAllBookTest {

    @Mock
    private BookService bookService;

    private List<Book> bookList;

    @BeforeEach
    public void setup() {
        bookList = new ArrayList<>();

        Book book1 = new Book();
        book1.setId(1L);
        book1.setName("The Great Gatsby");
        book1.setAuthor("Nelofar Zabi");
        book1.setDetails("This book write by Nelofar Zabi");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("The river");
        book2.setAuthor("Zuhra Hashimi");
        book2.setDetails("This book write by Zuhra Hashimi");

        bookList.add(book1);
        bookList.add(book2);
    }

    @Test
    public void testGetAllBooks() {
        when(bookService.getAllBooks()).thenReturn(bookList);
        List<Book> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(result.size(), 2);

        assertEquals(result.get(0).getName(), "The Great Gatsby");
        assertEquals(result.get(0).getAuthor(), "Nelofar Zabi");
        assertEquals(result.get(0).getDetails(), "This book write by Nelofar Zabi");

        assertEquals(result.get(1).getName(), "The river");
        assertEquals(result.get(1).getAuthor(), "Zuhra Hashimi");
        assertEquals(result.get(1).getDetails(), "This book write by Zuhra Hashimi");
        
        verify(bookService, times(1)).getAllBooks();
    }
}


