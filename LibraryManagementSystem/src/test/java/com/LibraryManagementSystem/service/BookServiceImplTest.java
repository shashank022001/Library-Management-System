package com.LibraryManagementSystem.service;



import com.LibraryManagementSystem.dto.BookDTO;
import com.LibraryManagementSystem.entity.Book;
import com.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.LibraryManagementSystem.repository.BookRepository;
import com.LibraryManagementSystem.serviceclasses.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        book = new Book(1, "Java Basics", "Author A", "Programming", 10, 5);
        bookDTO = new BookDTO();
    }

    @Test
    void testAddBook() {
        when(modelMapper.map(bookDTO, Book.class)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(modelMapper.map(book, BookDTO.class)).thenReturn(bookDTO);

        BookDTO saved = bookService.addBook(bookDTO);
        assertEquals(bookDTO.getTitle(), saved.getTitle());
    }

    @Test
    void testGetBookById() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(modelMapper.map(book, BookDTO.class)).thenReturn(bookDTO);

        BookDTO found = bookService.getBookById(1);
        assertEquals(bookDTO.getBookId(), found.getBookId());
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> bookService.getBookById(2));
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(book);
        when(bookRepository.findAll()).thenReturn(books);
        when(modelMapper.map(book, BookDTO.class)).thenReturn(bookDTO);

        List<BookDTO> bookDTOList = bookService.getAllBooks();
        assertEquals(1, bookDTOList.size());
    }

    @Test
    void testDeleteBook() {
        when(bookRepository.existsById(1)).thenReturn(true);
        bookService.deleteBook(1);
        verify(bookRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteBook_NotFound() {
        when(bookRepository.existsById(2)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> bookService.deleteBook(2));
    }
}

