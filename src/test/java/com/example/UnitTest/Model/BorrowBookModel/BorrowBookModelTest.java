package com.example.UnitTest.Model.BorrowBookModel;


import com.example.model.BorrowBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BorrowBookModelTest {

    private BorrowBook borrowBook;

    @BeforeEach
    public void setUp() {
        borrowBook = new BorrowBook();
    }

    @Test
    public void testSetAndGetUserId() {
        Long userId = 1L;
        borrowBook.setUserId(userId);
        assertEquals(userId, borrowBook.getUserId());
    }

    @Test
    public void testSetAndGetBookId() {
        Long bookId = 100L;
        borrowBook.setBookId(bookId);
        assertEquals(bookId, borrowBook.getBookId());
    }
}

