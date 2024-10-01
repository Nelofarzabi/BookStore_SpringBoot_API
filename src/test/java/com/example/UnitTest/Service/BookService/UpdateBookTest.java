package com.example.UnitTest.Service.BookService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.model.Book;
import com.example.repository.BookRepository;
import com.example.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UpdateBookTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book existingBook;
    private Book bookDetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        existingBook = new Book();
        existingBook.setId(1L);
        existingBook.setName("Old Book Name");
        existingBook.setAuthor("Old Author");
        existingBook.setDetails("Old Book Details");

        bookDetails = new Book();
        bookDetails.setName("New Book Name");
        bookDetails.setAuthor("New Author");
        bookDetails.setDetails("New Book Details");
    }

    @Test
    public void testUpdateBookExists() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(existingBook);
        Book updatedBook = bookService.updateBook(1L, bookDetails);

        assertNotNull(updatedBook);
        assertEquals("New Book Name", updatedBook.getName());
        assertEquals("New Author", updatedBook.getAuthor());
        assertEquals("New Book Details", updatedBook.getDetails());

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(existingBook);
    }

    @Test
    public void testUpdateBookDoesNotExist() {
        when(bookRepository.findById(1L)).thenReturn(java.util.Optional.empty());
        Book result = bookService.updateBook(1L, bookDetails);
        assertNull(result);
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, never()).save(any(Book.class));
    }
}

