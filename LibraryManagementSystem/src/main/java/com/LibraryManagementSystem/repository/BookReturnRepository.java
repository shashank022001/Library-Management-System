package com.LibraryManagementSystem.repository;




import com.LibraryManagementSystem.entity.BookReturn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReturnRepository extends JpaRepository<BookReturn, Integer> {
}

