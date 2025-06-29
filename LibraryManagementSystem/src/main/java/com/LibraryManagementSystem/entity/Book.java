package com.LibraryManagementSystem.entity;



import jakarta.persistence.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    private String title;
    private String author;
    private String category;
    private Integer totalCopies;
    private Integer availableCopies;

    public Book(int i, String javaBasics, String authorA, String programming, int i1, int i2) {
    }

  
}

