package com.example.book_service.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.book_service.model.Book;
import com.example.book_service.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    // 📌 Get all
    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    // 📌 Add
    public Book addBook(Book book) {
        return repo.save(book);
    }

    // 📌 Update
    public Book updateBook(Long id, Book updatedBook) {
        Book book = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setCategory(updatedBook.getCategory());
        book.setAvailable(updatedBook.isAvailable());

        return repo.save(book);
    }

    // 🔍 Search by title
    public List<Book> searchBooks(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }

    // 🔍 Search by category
    public List<Book> getBooksByCategory(String category) {
        return repo.findByCategoryContainingIgnoreCase(category);
    }

    // 🔥 Search by BOTH
    public List<Book> searchByTitleAndCategory(String title, String category) {
        return repo.findByTitleContainingIgnoreCaseAndCategoryContainingIgnoreCase(title, category);
    }

    // 📌 Delete
    public void deleteBook(Long id) {
        repo.deleteById(id);
    }
}