package com.LibraryManagementSystem.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DashboardStatsRepository extends JpaRepository<com.LibraryManagementSystem.entity.Book, Integer> {

    @Query("SELECT COUNT(b) FROM Book b")
    long countTotalBooks();

    @Query("SELECT COUNT(u) FROM User u")
    long countTotalUsers();

    @Query("SELECT COUNT(bb) FROM BorrowedBook bb WHERE bb.returnStatus = 'Not Returned'")
    long countBorrowedBooks();

    @Query("SELECT COUNT(bb) FROM BorrowedBook bb WHERE bb.returnStatus = 'Overdue'")
    long countOverdueBooks();
}

