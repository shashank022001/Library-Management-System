package com.LibraryManagementSystem.serviceclasses;

import com.LibraryManagementSystem.dto.BookDTO;
import com.LibraryManagementSystem.entity.Book;
import com.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.LibraryManagementSystem.repository.BookRepository;
import com.LibraryManagementSystem.serviceinterfaces.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookDTO addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        return modelMapper.map(bookRepository.save(book), BookDTO.class);
    }

    @Override
    public BookDTO getBookById(Integer bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));
        return modelMapper.map(book, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBook(Integer bookId, BookDTO bookDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + bookId));

        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCategory(bookDTO.getCategory());
        book.setTotalCopies(bookDTO.getTotalCopies());
        book.setAvailableCopies(bookDTO.getAvailableCopies());

        return modelMapper.map(bookRepository.save(book), BookDTO.class);
    }

    @Override
    public void deleteBook(Integer bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new ResourceNotFoundException("Book not found with ID: " + bookId);
        }
        bookRepository.deleteById(bookId);
    }
}
