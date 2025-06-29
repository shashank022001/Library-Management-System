package com.LibraryManagementSystem.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FineRepository extends JpaRepository<com.LibraryManagementSystem.entity.Fine, Integer> {
    List<com.LibraryManagementSystem.entity.Fine> findByBorrowedBookUserUserIdAndPaidStatus(Integer userId, String paidStatus);
}

