package com.LibraryManagementSystem.dto;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BookDTO {
    private Integer bookId;
    private String title;
    private String author;
    private String category;
    private Integer totalCopies;
    private Integer availableCopies;

   
}

