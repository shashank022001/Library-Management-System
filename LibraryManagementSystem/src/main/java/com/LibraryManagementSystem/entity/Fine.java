package com.LibraryManagementSystem.entity;



import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fineId;

    @OneToOne
    @JoinColumn(name = "borrow_id")
    private BorrowedBook borrowedBook;

    private BigDecimal fineAmount;
    private String paidStatus; // e.g., Paid, Unpaid

   
}

