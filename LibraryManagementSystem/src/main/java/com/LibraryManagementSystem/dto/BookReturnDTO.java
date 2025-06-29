package com.LibraryManagementSystem.dto;



import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookReturnDTO {
    private Integer returnId;
    private Integer borrowId;
    private LocalDate returnDate;
    private String condition;
    private Integer fineId;

   
}
