package com.LibraryManagementSystem.controller;

import com.LibraryManagementSystem.dto.FineDTO;
import com.LibraryManagementSystem.serviceinterfaces.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fines")
public class FineController {

    @Autowired
    private FineService fineService;

    @GetMapping("/user/{userId}/unpaid")
    public List<FineDTO> getUnpaidFinesByUser(@PathVariable Integer userId) {
        return fineService.getUnpaidFinesByUser(userId);
    }

    @PostMapping("/settle")
    public ResponseEntity<String> settleFine(@RequestParam Integer userId, @RequestParam Integer fineId) {
        return ResponseEntity.ok(fineService.settleFine(userId, fineId));
    }
}

