package com.LibraryManagementSystem.service;



import com.LibraryManagementSystem.dto.FineDTO;
import com.LibraryManagementSystem.entity.BorrowedBook;
import com.LibraryManagementSystem.entity.Fine;
import com.LibraryManagementSystem.entity.User;
import com.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.LibraryManagementSystem.repository.FineRepository;
import com.LibraryManagementSystem.serviceclasses.FineServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FineServiceImplTest {

    @Mock
    private FineRepository fineRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FineServiceImpl fineService;

    private Fine fine;
    private FineDTO fineDTO;
    private BorrowedBook borrowedBook;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setUserId(1);

        borrowedBook = new BorrowedBook();
        borrowedBook.setUser(user);

        fine = new Fine();
        fine.setFineId(1);
        fine.setFineAmount(new BigDecimal(100.0));
        fine.setPaidStatus("Unpaid");
        fine.setBorrowedBook(borrowedBook);

        fineDTO = new FineDTO();
        fine.setFineId(1);
        fine.setFineAmount(new BigDecimal(100.0));
        fineDTO.setPaidStatus("Unpaid");
    }

    @Test
    void testGetUnpaidFinesByUser() {
        when(fineRepository.findByBorrowedBookUserUserIdAndPaidStatus(1, "Unpaid"))
                .thenReturn(Arrays.asList(fine));
        when(modelMapper.map(fine, FineDTO.class)).thenReturn(fineDTO);

        var result = fineService.getUnpaidFinesByUser(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Unpaid", result.get(0).getPaidStatus());
    }

    @Test
    void testSettleFineSuccess() {
        when(fineRepository.findById(1)).thenReturn(Optional.of(fine));

        String response = fineService.settleFine(1, 1);

        assertEquals("Fine settled successfully.", response);
        verify(fineRepository, times(1)).save(fine);
    }

    @Test
    void testSettleFineThrowsNotFound() {
        when(fineRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> fineService.settleFine(1, 1));
    }

    @Test
    void testSettleFineThrowsIllegalArgument() {
        user.setUserId(2); // Mismatch
        when(fineRepository.findById(1)).thenReturn(Optional.of(fine));

        assertThrows(IllegalArgumentException.class, () -> fineService.settleFine(1, 1));
    }
}
