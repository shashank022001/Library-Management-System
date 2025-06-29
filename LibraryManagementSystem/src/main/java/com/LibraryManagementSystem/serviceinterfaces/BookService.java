package com.LibraryManagementSystem.serviceinterfaces;



import com.LibraryManagementSystem.dto.BookDTO;
import java.util.List;

public interface BookService {
    BookDTO addBook(BookDTO bookDTO);
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Integer bookId);
    BookDTO updateBook(Integer bookId, BookDTO bookDTO);
    void deleteBook(Integer bookId);
}

