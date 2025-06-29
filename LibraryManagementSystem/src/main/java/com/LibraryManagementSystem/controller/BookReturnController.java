package com.LibraryManagementSystem.controller;

import com.LibraryManagementSystem.dto.BookReturnDTO;
import com.LibraryManagementSystem.serviceinterfaces.BookReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returns")
public class BookReturnController {

    @Autowired
    private BookReturnService bookReturnService;

    @PostMapping
    public ResponseEntity<BookReturnDTO> returnBook(@RequestBody BookReturnDTO dto) {
        return ResponseEntity.ok(bookReturnService.returnBook(dto));
    }

    @GetMapping
    public List<BookReturnDTO> getAllReturns() {
        return bookReturnService.getAllReturns();
    }
}

