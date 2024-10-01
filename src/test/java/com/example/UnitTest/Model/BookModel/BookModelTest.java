package com.example.UnitTest.Model.BookModel;

import com.example.model.Book;
import com.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BookModelTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book();
    }

    @Test
    public void testSetAndGetId() {
        Long id = 1L;
        book.setId(id);
        assertEquals(id, book.getId());
    }

    @Test
    public void testSetAndGetName() {
        String name = "Test Book";
        book.setName(name);
        assertEquals(name, book.getName());
    }

    @Test
    public void testSetAndGetAuthor() {
        String author = "Test Author";
        book.setAuthor(author);
        assertEquals(author, book.getAuthor());
    }

    @Test
    public void testSetAndGetDetails() {
        String details = "Test Details";
        book.setDetails(details);
        assertEquals(details, book.getDetails());
    }

    @Test
    public void testSetAndGetUsers() {
        Set<User> users = new HashSet<>();

        User user1 = new User();
        user1.setId(1L);
        user1.setName("User 1");

        User user2 = new User();
        user2.setId(2L);
        user2.setName("User 2");

        users.add(user1);
        users.add(user2);

        book.setUsers(users);

        Set<User> retrievedUsers = book.getUsers();
        assertNotNull(retrievedUsers);
        assertEquals(2, retrievedUsers.size());
        assertTrue(retrievedUsers.contains(user1));
        assertTrue(retrievedUsers.contains(user2));
    }

    @Test
    public void testConstructorWithArgs() {
        Long id = 1L;
        String name = "Test Book";
        String author = "Test Author";
        String details = "Test Details";

        Book bookWithArgs = new Book(id, name, author, details);

        assertEquals(id, bookWithArgs.getId());
        assertEquals(name, bookWithArgs.getName());
        assertEquals(author, bookWithArgs.getAuthor());
        assertEquals(details, bookWithArgs.getDetails());
    }
}

