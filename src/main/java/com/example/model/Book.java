package com.example.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Entity @Getter @Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String details;

    @ManyToMany(mappedBy = "borrowedBooks")
    private Set<User> users = new HashSet<>();

    public Book(){}

    public Book(Long id , String name ,  String author , String details){
        this.id = id;
        this.name = name;
        this.author = author;
        this.details = details;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
