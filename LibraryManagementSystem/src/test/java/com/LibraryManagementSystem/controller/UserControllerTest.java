package com.LibraryManagementSystem.controller;



import com.LibraryManagementSystem.dto.UserDTO;
import com.LibraryManagementSystem.serviceinterfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDTO = new UserDTO();
        userDTO.setUserId(1);
        userDTO.setName("Test User");
        userDTO.setUsername("testuser");
        userDTO.setPassword("pass");
        userDTO.setEmail("test@example.com");
    }

    @Test
    void createUserTest() {
        when(userService.createUser(any(UserDTO.class))).thenReturn(userDTO);
        ResponseEntity<UserDTO> response = userController.createUser(userDTO);

        assertNotNull(response.getBody());
        assertEquals("Test User", response.getBody().getName());
    }

    @Test
    void getUserByIdTest() {
        when(userService.getUserById(1)).thenReturn(userDTO);
        ResponseEntity<UserDTO> response = userController.getUserById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getUserId());
    }

    @Test
    void getAllUsersTest() {
        when(userService.getAllUsers()).thenReturn(Arrays.asList(userDTO));
        List<UserDTO> users = userController.getAllUsers();

        assertEquals(1, users.size());
    }

    @Test
    void updateUserTest() {
        when(userService.updateUser(eq(1), any(UserDTO.class))).thenReturn(userDTO);
        ResponseEntity<UserDTO> response = userController.updateUser(1, userDTO);

        assertEquals("Test User", response.getBody().getName());
    }

    @Test
    void deleteUserTest() {
        doNothing().when(userService).deleteUser(1);
        ResponseEntity<String> response = userController.deleteUser(1);

        assertEquals("User deleted successfully", response.getBody());
    }

    @Test
    void signInTest() {
        when(userService.signIn("testuser", "pass")).thenReturn("Sign-in successful for user: testuser");
        ResponseEntity<String> response = userController.signIn("testuser", "pass");

        assertTrue(response.getBody().contains("successful"));
    }

    @Test
    void signOutTest() {
        when(userService.signOut("testuser")).thenReturn("Sign-out successful for user: testuser");
        ResponseEntity<String> response = userController.signOut("testuser");

        assertTrue(response.getBody().contains("successful"));
    }
}
