package com.example.service;

import com.example.model.Book;
import com.example.model.User;
import com.example.repository.BookRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setLastname(userDetails.getLastname());
            user.setGender(userDetails.getGender());
            user.setAddress(userDetails.getAddress());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public User borrowBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user != null && book != null) {
            user.getBorrowedBooks().add(book);
            return userRepository.save(user);
        }
        return null;
    }

    public User returnBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElse(null);
        Book book = bookRepository.findById(bookId).orElse(null);

        if (user != null && book != null) {
            user.getBorrowedBooks().remove(book);
            return userRepository.save(user);
        }
        return null;
    }

}
