package com.LibraryManagementSystem.entity;



import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BookReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer returnId;

    @OneToOne
    @JoinColumn(name = "borrow_id")
    private BorrowedBook borrowedBook;

    private LocalDate returnDate;
    private String bookCondition; // e.g., Good, Damaged

   
}

