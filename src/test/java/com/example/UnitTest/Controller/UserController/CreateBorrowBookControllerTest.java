package com.example.UnitTest.Controller.UserController;

import com.example.controller.UserController;
import com.example.model.BorrowBook;
import com.example.model.User;
import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateBorrowBookControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private BorrowBook borrowBook;
    private User mockUser;

    @BeforeEach
    public void setup() {
        borrowBook = new BorrowBook();
        borrowBook.setUserId(1L);
        borrowBook.setBookId(101L);

        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Nelofar Zabi");
    }

    @Test
    public void testBorrowBookSuccess() {
        when(userService.borrowBook(borrowBook.getUserId(), borrowBook.getBookId())).thenReturn(mockUser);
        ResponseEntity<String> response = userController.borrowBook(borrowBook);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book successfully borrowed by User ID: 1", response.getBody());
        verify(userService, times(1)).borrowBook(borrowBook.getUserId(), borrowBook.getBookId());
    }

    @Test
    public void testBorrowBookUserOrBookNotFound() {
        when(userService.borrowBook(borrowBook.getUserId(), borrowBook.getBookId())).thenReturn(null);
        ResponseEntity<String> response = userController.borrowBook(borrowBook);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed to borrow the book. Either the user or the book was not found.", response.getBody());
        verify(userService, times(1)).borrowBook(borrowBook.getUserId(), borrowBook.getBookId());
    }

    @Test
    public void testBorrowBookInternalServerError() {
        when(userService.borrowBook(borrowBook.getUserId(), borrowBook.getBookId())).thenThrow(new RuntimeException("Database error"));
        ResponseEntity<String> response = userController.borrowBook(borrowBook);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while borrowing the book: Database error", response.getBody());
        verify(userService, times(1)).borrowBook(borrowBook.getUserId(), borrowBook.getBookId());
    }
}
