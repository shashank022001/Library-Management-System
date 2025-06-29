package com.LibraryManagementSystem.serviceclasses;

import com.LibraryManagementSystem.dto.BookReturnDTO;
import com.LibraryManagementSystem.entity.BookReturn;
import com.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.LibraryManagementSystem.repository.BookReturnRepository;
import com.LibraryManagementSystem.serviceinterfaces.BookReturnService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookReturnServiceImpl implements BookReturnService {

    @Autowired
    private BookReturnRepository bookReturnRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookReturnDTO returnBook(BookReturnDTO dto) {
        BookReturn bookReturn = modelMapper.map(dto, BookReturn.class);
        return modelMapper.map(bookReturnRepository.save(bookReturn), BookReturnDTO.class);
    }

    @Override
    public List<BookReturnDTO> getAllReturns() {
        return bookReturnRepository.findAll().stream()
                .map(returned -> modelMapper.map(returned, BookReturnDTO.class))
                .collect(Collectors.toList());
    }


}
