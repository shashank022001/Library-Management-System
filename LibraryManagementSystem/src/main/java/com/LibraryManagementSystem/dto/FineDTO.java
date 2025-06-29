package com.LibraryManagementSystem.dto;



import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FineDTO {
    private Integer fineId;
    private Integer borrowId;
    private BigDecimal fineAmount;
    private String paidStatus;

   
}

