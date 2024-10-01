package com.example.UnitTest.Service.UserService;

import com.example.model.Book;
import com.example.model.User;
import com.example.repository.BookRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReturnBookByUserTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private UserService userService;

    private User mockUser;
    private Book mockBook;

    @BeforeEach
    public void setup() {
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Nelofar Zabi");

        mockBook = new Book();
        mockBook.setId(101L);
        mockBook.setName("The River");

    }

    @Test
    public void testReturnBookSuccess() {
        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.of(mockUser));
        when(bookRepository.findById(mockBook.getId())).thenReturn(Optional.of(mockBook));
        when(userRepository.save(mockUser)).thenReturn(mockUser);
        User result = userService.returnBook(mockUser.getId(), mockBook.getId());
        assertNotNull(result);
        assertFalse(result.getBorrowedBooks().contains(mockBook));

        verify(userRepository, times(1)).findById(mockUser.getId());
        verify(bookRepository, times(1)).findById(mockBook.getId());
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    public void testReturnBookUserNotFound() {
        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.empty());
        when(bookRepository.findById(mockBook.getId())).thenReturn(Optional.of(mockBook));
        User result = userService.returnBook(mockUser.getId(), mockBook.getId());
        assertNull(result);
        verify(userRepository, times(1)).findById(mockUser.getId());
        verify(bookRepository, times(1)).findById(mockBook.getId());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testReturnBookNotFound() {
        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.of(mockUser));
        when(bookRepository.findById(mockBook.getId())).thenReturn(Optional.empty());
        User result = userService.returnBook(mockUser.getId(), mockBook.getId());
        assertNull(result);
        verify(userRepository, times(1)).findById(mockUser.getId());
        verify(bookRepository, times(1)).findById(mockBook.getId());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testReturnBookUserAndBookNotFound() {
        when(userRepository.findById(mockUser.getId())).thenReturn(Optional.empty());
        when(bookRepository.findById(mockBook.getId())).thenReturn(Optional.empty());
        User result = userService.returnBook(mockUser.getId(), mockBook.getId());
        assertNull(result);
        verify(userRepository, times(1)).findById(mockUser.getId());
        verify(bookRepository, times(1)).findById(mockBook.getId());
        verify(userRepository, never()).save(any(User.class));
    }
}

