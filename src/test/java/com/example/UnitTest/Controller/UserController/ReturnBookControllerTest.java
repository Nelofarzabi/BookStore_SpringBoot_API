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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReturnBookControllerTest {

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
    public void testReturnBookSuccess() {
        when(userService.returnBook(borrowBook.getUserId(), borrowBook.getBookId())).thenReturn(mockUser);
        ResponseEntity<String> response = userController.returnBook(borrowBook);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book successfully returned by User ID: 1", response.getBody());
        verify(userService, times(1)).returnBook(borrowBook.getUserId(), borrowBook.getBookId());
    }

    @Test
    public void testReturnBookUserOrBookNotFound() {
        when(userService.returnBook(borrowBook.getUserId(), borrowBook.getBookId())).thenReturn(null);
        ResponseEntity<String> response = userController.returnBook(borrowBook);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed to return the book. Either the user or the book was not found.", response.getBody());
        verify(userService, times(1)).returnBook(borrowBook.getUserId(), borrowBook.getBookId());
    }

    @Test
    public void testReturnBookInternalServerError() {
        when(userService.returnBook(borrowBook.getUserId(), borrowBook.getBookId())).thenThrow(new RuntimeException("Database error"));
        ResponseEntity<String> response = userController.returnBook(borrowBook);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while returning the book: Database error", response.getBody());
        verify(userService, times(1)).returnBook(borrowBook.getUserId(), borrowBook.getBookId());
    }
}
