package com.library.borrowservice.exception;

public class BorrowNotFoundException extends RuntimeException {
    public BorrowNotFoundException(Long id) {
        super("Borrow not found with id " + id);
    }
}
