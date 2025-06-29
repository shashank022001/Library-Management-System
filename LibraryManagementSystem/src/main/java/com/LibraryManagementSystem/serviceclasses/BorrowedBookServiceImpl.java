package com.LibraryManagementSystem.serviceclasses;

import com.LibraryManagementSystem.dto.BorrowedBookDTO;
import com.LibraryManagementSystem.entity.BorrowedBook;
import com.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.LibraryManagementSystem.repository.BorrowedBookRepository;
import com.LibraryManagementSystem.serviceinterfaces.BorrowedBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowedBookServiceImpl implements BorrowedBookService {

    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BorrowedBookDTO borrowBook(BorrowedBookDTO borrowedBookDTO) {
        BorrowedBook borrowedBook = modelMapper.map(borrowedBookDTO, BorrowedBook.class);
        BorrowedBook saved = borrowedBookRepository.save(borrowedBook);
        return modelMapper.map(saved, BorrowedBookDTO.class);
    }

    @Override
    public List<BorrowedBookDTO> getAllBorrowedBooks() {
        return borrowedBookRepository.findAll()
                .stream()
                .map(b -> modelMapper.map(b, BorrowedBookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public BorrowedBookDTO getBorrowedBookById(Integer borrowId) {
        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowed Book not found with ID: " + borrowId));
        return modelMapper.map(borrowedBook, BorrowedBookDTO.class);
    }

    @Override
    public BorrowedBookDTO updateBorrowedBook(Integer borrowId, BorrowedBookDTO borrowedBookDTO) {
        BorrowedBook existing = borrowedBookRepository.findById(borrowId)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowed Book not found with ID: " + borrowId));

        existing.setBook(borrowedBookDTO.getBook());
        existing.setUser(borrowedBookDTO.getUser());
        existing.setBorrowDate(borrowedBookDTO.getBorrowDate());
        existing.setDueDate(borrowedBookDTO.getDueDate());
        existing.setReturnStatus(borrowedBookDTO.getReturnStatus());

        BorrowedBook updated = borrowedBookRepository.save(existing);
        return modelMapper.map(updated, BorrowedBookDTO.class);
    }

    @Override
    public void deleteBorrowedBook(Integer borrowId) {
        if (!borrowedBookRepository.existsById(borrowId)) {
            throw new ResourceNotFoundException("Borrowed Book not found with ID: " + borrowId);
        }
        borrowedBookRepository.deleteById(borrowId);
    }
}
