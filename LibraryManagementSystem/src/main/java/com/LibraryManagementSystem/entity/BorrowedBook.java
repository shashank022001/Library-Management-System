package com.LibraryManagementSystem.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer borrowId;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date borrowDate;
    private Date dueDate;

    private String returnStatus;

    // Getters and Setters
   
}
