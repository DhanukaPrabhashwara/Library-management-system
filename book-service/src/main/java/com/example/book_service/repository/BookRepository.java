package com.example.book_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_service.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    // 🔍 Search by title (partial match)
    List<Book> findByTitleContaining(String title);

    // 🔍 Search by category (partial match)
    List<Book> findByCategoryContaining(String category);

    // 🔍 Search by BOTH title AND category
    List<Book> findByTitleContainingAndCategoryContaining(String title, String category);

    // 🔥 BONUS (case insensitive search)
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByCategoryContainingIgnoreCase(String category);
    List<Book> findByTitleContainingIgnoreCaseAndCategoryContainingIgnoreCase(String title, String category);
}