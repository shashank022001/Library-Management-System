package com.LibraryManagementSystem.dto;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDTO {
    private long totalBooks;
    private long totalUsers;
    private long borrowedBooks;
    private long overdueBooks;

   
}

