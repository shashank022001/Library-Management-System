package com.LibraryManagementSystem.controller;



import com.LibraryManagementSystem.dto.BookDTO;
import com.LibraryManagementSystem.serviceinterfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer bookId, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(bookId, bookDTO));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("Book deleted successfully");
    }
}
