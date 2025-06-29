package com.LibraryManagementSystem.serviceinterfaces;



import com.LibraryManagementSystem.dto.BorrowedBookDTO;
import java.util.List;


public interface BorrowedBookService {

    // Create
    BorrowedBookDTO borrowBook(BorrowedBookDTO borrowedBookDTO);

    // Read all
    List<BorrowedBookDTO> getAllBorrowedBooks();

    // Read by ID
    BorrowedBookDTO getBorrowedBookById(Integer borrowId);

    // Update (optional, only if you want to allow updates to borrow info)
    BorrowedBookDTO updateBorrowedBook(Integer borrowId, BorrowedBookDTO borrowedBookDTO);

    // Delete
    void deleteBorrowedBook(Integer borrowId);
}


