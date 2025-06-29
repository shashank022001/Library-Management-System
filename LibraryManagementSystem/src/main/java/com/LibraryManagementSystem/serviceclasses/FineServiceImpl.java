package com.LibraryManagementSystem.serviceclasses;

import com.LibraryManagementSystem.dto.FineDTO;
import com.LibraryManagementSystem.entity.Fine;
import com.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.LibraryManagementSystem.repository.FineRepository;
import com.LibraryManagementSystem.serviceinterfaces.FineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FineServiceImpl implements FineService {

    @Autowired
    private FineRepository fineRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FineDTO> getUnpaidFinesByUser(Integer userId) {
        return fineRepository.findByBorrowedBookUserUserIdAndPaidStatus(userId, "Unpaid")
                .stream()
                .map(fine -> modelMapper.map(fine, FineDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String settleFine(Integer userId, Integer fineId) {
        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() -> new ResourceNotFoundException("Fine not found"));

        if (!fine.getBorrowedBook().getUser().getUserId().equals(userId)) {
            throw new IllegalArgumentException("Fine does not belong to user");
        }

        fine.setPaidStatus("Paid");
        fineRepository.save(fine);
        return "Fine settled successfully.";
    }


}
