package com.example.UnitTest.Model.UserModel;

import com.example.model.Book;
import com.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserModelTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testSetAndGetId() {
        Long id = 1L;
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void testSetAndGetName() {
        String name = "Nelofar";
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    public void testSetAndGetLastName() {
        String lastName = "Zabi";
        user.setLastname(lastName);
        assertEquals(lastName, user.getLastname());
    }

    @Test
    public void testSetAndGetGender() {
        String gender = "Female";
        user.setGender(gender);
        assertEquals(gender, user.getGender());
    }

    @Test
    public void testSetAndGetAddress() {
        String address = "Kabul, Afghanistan";
        user.setAddress(address);
        assertEquals(address, user.getAddress());
    }

    @Test
    public void testSetAndGetEmail() {
        String email = "nelofar@example.com";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void testSetAndGetBorrowedBooks() {
        Set<Book> books = new HashSet<>();
        Book book1 = new Book();
        book1.setId(1L);
        book1.setName("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("Book 2");

        books.add(book1);
        books.add(book2);

        user.setBorrowedBooks(books);

        Set<Book> retrievedBooks = user.getBorrowedBooks();
        assertNotNull(retrievedBooks);
        assertEquals(2, retrievedBooks.size());
        assertTrue(retrievedBooks.contains(book1));
        assertTrue(retrievedBooks.contains(book2));
    }
}

