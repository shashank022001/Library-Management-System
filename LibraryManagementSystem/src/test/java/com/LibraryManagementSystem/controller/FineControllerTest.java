package com.LibraryManagementSystem.controller;



import com.LibraryManagementSystem.dto.FineDTO;
import com.LibraryManagementSystem.serviceinterfaces.FineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FineControllerTest {

    @Mock
    private FineService fineService;

    @InjectMocks
    private FineController fineController;

    private FineDTO fineDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fineDTO = new FineDTO();
        fineDTO.setFineId(1);
        fineDTO.setFineAmount(new BigDecimal(100.0));
        fineDTO.setPaidStatus("Unpaid");
    }

    @Test
    void testGetUnpaidFinesByUser() {
        when(fineService.getUnpaidFinesByUser(1)).thenReturn(Arrays.asList(fineDTO));

        List<FineDTO> result = fineController.getUnpaidFinesByUser(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(fineService, times(1)).getUnpaidFinesByUser(1);
    }

    @Test
    void testSettleFine() {
        when(fineService.settleFine(1, 1)).thenReturn("Fine settled successfully.");

        ResponseEntity<String> response = fineController.settleFine(1, 1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Fine settled successfully.", response.getBody());
        verify(fineService, times(1)).settleFine(1, 1);
    }
}
