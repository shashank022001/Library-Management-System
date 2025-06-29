package com.LibraryManagementSystem.repository;



import com.LibraryManagementSystem.entity.BorrowedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {

    // Custom finder to retrieve borrowed books by user ID
    List<BorrowedBook> findByUserUserId(Integer userId);

    // Optionally, find by return status
    List<BorrowedBook> findByReturnStatus(String returnStatus);
}
