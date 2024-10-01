package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        try {
            Book createdBook = bookService.createBook(book);
            if (createdBook != null) {
                return ResponseEntity.ok("Book successfully created.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book creation failed.");
            }
           } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        try {
            Book updatedBook = bookService.updateBook(id, bookDetails);
            if (updatedBook != null) {
                return ResponseEntity.ok("Book successfully updated.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to update the book.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        try {
            boolean isDeleted = bookService.deleteBook(id);
            if (isDeleted) {
                return ResponseEntity.ok("Book successfully deleted.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete the book.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

}
