package com.library.borrowservice.controller;

import com.library.borrowservice.dto.BorrowDTO;
import com.library.borrowservice.model.Borrow;
import com.library.borrowservice.service.BorrowService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public ResponseEntity<BorrowDTO> createBorrow(@RequestBody BorrowDTO borrowDTO) {
        Borrow created = borrowService.createBorrow(toEntity(borrowDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(created));
    }

    @GetMapping
    public ResponseEntity<List<BorrowDTO>> getAllBorrows() {
        List<BorrowDTO> borrows = borrowService.getAllBorrows()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(borrows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowDTO> getBorrowById(@PathVariable Long id) {
        Borrow borrow = borrowService.getBorrowById(id);
        return ResponseEntity.ok(toDto(borrow));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowDTO> updateBorrow(@PathVariable Long id, @RequestBody BorrowDTO borrowDTO) {
        Borrow updated = borrowService.updateBorrow(id, toEntity(borrowDTO));
        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrow(@PathVariable Long id) {
        borrowService.deleteBorrow(id);
        return ResponseEntity.ok().build();
    }

    private Borrow toEntity(BorrowDTO dto) {
        return new Borrow(
                dto.getId(),
                dto.getMemberId(),
                dto.getBookId(),
                dto.getBorrowDate(),
                dto.getDueDate(),
                dto.getReturnDate(),
                dto.getStatus()
        );
    }

    private BorrowDTO toDto(Borrow borrow) {
        return new BorrowDTO(
                borrow.getId(),
                borrow.getMemberId(),
                borrow.getBookId(),
                borrow.getBorrowDate(),
                borrow.getDueDate(),
                borrow.getReturnDate(),
                borrow.getStatus()
        );
    }
}
