package com.library.borrowservice.service;

import com.library.borrowservice.exception.BorrowNotFoundException;
import com.library.borrowservice.model.Borrow;
import com.library.borrowservice.repository.BorrowRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {
    private final BorrowRepository borrowRepository;

    public BorrowService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    public Borrow createBorrow(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow getBorrowById(Long id) {
        return borrowRepository.findById(id).orElseThrow(() -> new BorrowNotFoundException(id));
    }

    public Borrow updateBorrow(Long id, Borrow updatedBorrow) {
        Borrow existing = getBorrowById(id);
        existing.setMemberId(updatedBorrow.getMemberId());
        existing.setBookId(updatedBorrow.getBookId());
        existing.setBorrowDate(updatedBorrow.getBorrowDate());
        existing.setDueDate(updatedBorrow.getDueDate());
        existing.setReturnDate(updatedBorrow.getReturnDate());
        existing.setStatus(updatedBorrow.getStatus());
        return borrowRepository.save(existing);
    }

    public void deleteBorrow(Long id) {
        Borrow existing = getBorrowById(id);
        borrowRepository.delete(existing);
    }
}
