package com.LibraryManagementSystem.controller;

import com.LibraryManagementSystem.dto.BorrowedBookDTO;
import com.LibraryManagementSystem.serviceinterfaces.BorrowedBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowed-books")
public class BorrowedBookController {

    @Autowired
    private BorrowedBookService borrowedBookService;

    @PostMapping
    public ResponseEntity<BorrowedBookDTO> borrowBook(@RequestBody BorrowedBookDTO borrowedBookDTO) {
        return ResponseEntity.ok(borrowedBookService.borrowBook(borrowedBookDTO));
    }

    @GetMapping
    public List<BorrowedBookDTO> getAllBorrowedBooks() {
        return borrowedBookService.getAllBorrowedBooks();
    }

    @GetMapping("/{borrowId}")
    public ResponseEntity<BorrowedBookDTO> getBorrowedBookById(@PathVariable Integer borrowId) {
        return ResponseEntity.ok(borrowedBookService.getBorrowedBookById(borrowId));
    }

    @PutMapping("/{borrowId}")
    public ResponseEntity<BorrowedBookDTO> updateBorrowedBook(@PathVariable Integer borrowId, @RequestBody BorrowedBookDTO borrowedBookDTO) {
        return ResponseEntity.ok(borrowedBookService.updateBorrowedBook(borrowId, borrowedBookDTO));
    }

    @DeleteMapping("/{borrowId}")
    public ResponseEntity<String> deleteBorrowedBook(@PathVariable Integer borrowId) {
        borrowedBookService.deleteBorrowedBook(borrowId);
        return ResponseEntity.ok("Borrowed book record deleted successfully");
    }
}

