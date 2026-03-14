package com.klu.controller;

import com.klu.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryController {

    private List<Book> bookList = new ArrayList<>();

    
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to KLU Online Library System!";
    }

   
    @GetMapping("/count")
    public int getCount() {
        return 150;
    }

    
    @GetMapping("/price")
    public double getPrice() {
        return 499.99;
    }

  
    @GetMapping("/books")
    public List<String> getBooks() {
        List<String> titles = new ArrayList<>();
        titles.add("Java Programming");
        titles.add("Spring Boot Guide");
        titles.add("Data Structures");
        return titles;
    }

    
    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable int id) {
        return "Details of Book with ID: " + id;
    }

    
    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        return "Searching for book titled: " + title;
    }

  
    @GetMapping("/author/{name}")
    public String getAuthor(@PathVariable String name) {
        return "Books written by Author: " + name;
    }

   
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookList.add(book);
        return "Book added successfully!";
    }

    
    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return bookList;
    }
}
