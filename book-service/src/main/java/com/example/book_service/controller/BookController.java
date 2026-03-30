package com.example.book_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.book_service.model.Book;
import com.example.book_service.services.BookService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // 📌 Get all books
    @GetMapping
    public List<Book> getAll() {
        return service.getAllBooks();
    }

    // 📌 Add book
    @PostMapping
    public Book add(@RequestBody Book book) {
        return service.addBook(book);
    }

    // 🔍 Search by title
    @GetMapping("/search")
    public List<Book> searchByTitle(@RequestParam String title) {
        return service.searchBooks(title);
    }

    // 🔍 Search by category
    @GetMapping("/category")
    public List<Book> searchByCategory(@RequestParam String category) {
        return service.getBooksByCategory(category);
    }

    // 🔥 Search by title + category
    @GetMapping("/search/filter")
    public List<Book> searchByTitleAndCategory(
            @RequestParam String title,
            @RequestParam String category) {
        return service.searchByTitleAndCategory(title, category);
    }

    // 📌 Update book
    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        return service.updateBook(id, book);
    }

    // 📌 Delete book
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteBook(id);
    }
}