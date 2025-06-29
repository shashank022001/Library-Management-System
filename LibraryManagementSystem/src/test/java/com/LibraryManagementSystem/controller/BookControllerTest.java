package com.LibraryManagementSystem.controller;



import com.LibraryManagementSystem.dto.BookDTO;
import com.LibraryManagementSystem.serviceinterfaces.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
       BookDTO bookDTO = new BookDTO();
        bookDTO.setBookId(1);
        bookDTO.setTitle("Java Book");
    }





    @Test
    void testGetAllBooks() {
        when(bookService.getAllBooks()).thenReturn(Arrays.asList(bookDTO));

        List<BookDTO> books = bookController.getAllBooks();

        assertEquals(1, books.size());
        verify(bookService, times(1)).getAllBooks();
    }



    @Test
    void testDeleteBook() {
        doNothing().when(bookService).deleteBook(1);

        ResponseEntity<String> response = bookController.deleteBook(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Book deleted successfully", response.getBody());
        verify(bookService, times(1)).deleteBook(1);
    }
}
