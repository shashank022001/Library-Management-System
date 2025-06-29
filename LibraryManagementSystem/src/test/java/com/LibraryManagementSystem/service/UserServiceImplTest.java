package com.LibraryManagementSystem.service;



import com.LibraryManagementSystem.dto.UserDTO;
import com.LibraryManagementSystem.entity.User;
import com.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.LibraryManagementSystem.repository.UserRepository;
import com.LibraryManagementSystem.serviceclasses.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        userDTO = new UserDTO(1, "John Doe", "john@example.com", "johndoe", "pass123", null);
    }





    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1));
    }



    @Test
    void testUpdateUser_Success() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO result = userService.updateUser(1, userDTO);

        assertNotNull(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser_Success() {
        when(userRepository.existsById(1)).thenReturn(true);
        userService.deleteUser(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void testDeleteUser_NotFound() {
        when(userRepository.existsById(1)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(1));
    }





    @Test
    void testSignOut_ByUsername() {
        when(userRepository.findByUsername("johndoe")).thenReturn(Optional.of(user));
        String result = userService.signOut("johndoe");
        assertEquals("Sign-out successful for user: johndoe", result);
    }


}
