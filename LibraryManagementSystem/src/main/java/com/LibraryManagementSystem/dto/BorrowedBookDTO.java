package com.LibraryManagementSystem.dto;

import com.LibraryManagementSystem.entity.Book;
import com.LibraryManagementSystem.entity.User;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowedBookDTO {
    private Integer borrowId;
    private Book book;
    private User user;
    private Date borrowDate;
    private Date dueDate;
    private String returnStatus;

   
}
