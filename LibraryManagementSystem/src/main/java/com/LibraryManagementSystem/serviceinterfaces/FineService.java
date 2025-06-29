package com.LibraryManagementSystem.serviceinterfaces;



import com.LibraryManagementSystem.dto.FineDTO;
import java.util.List;

public interface FineService {
    List<FineDTO> getUnpaidFinesByUser(Integer userId);
    String settleFine(Integer userId, Integer fineId);
}

