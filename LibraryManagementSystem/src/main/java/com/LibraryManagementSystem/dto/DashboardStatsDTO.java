package com.LibraryManagementSystem.dto;


@Entity
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

