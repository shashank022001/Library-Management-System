package com.LibraryManagementSystem.serviceinterfaces;



import com.LibraryManagementSystem.dto.BookReturnDTO;
import java.util.List;

public interface BookReturnService {
    BookReturnDTO returnBook(BookReturnDTO returnDTO);
    List<BookReturnDTO> getAllReturns();
}

