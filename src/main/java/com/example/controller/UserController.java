package com.example.controller;

import com.example.model.BorrowBook;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            if (createdUser != null) {
                return ResponseEntity.ok("User successfully created");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            if (updatedUser != null) {
                return ResponseEntity.ok("User successfully updated");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.ok("User successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found. Deletion failed.");
        }
    }

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestBody BorrowBook borrowBook) {
        try {
            User user = userService.borrowBook(borrowBook.getUserId(), borrowBook.getBookId());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to borrow the book. Either the user or the book was not found.");
            }
            return ResponseEntity.ok("Book successfully borrowed by User ID: " + borrowBook.getUserId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while borrowing the book: " + e.getMessage());
        }
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody BorrowBook borrowBook) {
        try {
            User user = userService.returnBook(borrowBook.getUserId(), borrowBook.getBookId());
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to return the book. Either the user or the book was not found.");
            }
            return ResponseEntity.ok("Book successfully returned by User ID: " + borrowBook.getUserId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while returning the book: " + e.getMessage());
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
