package com.LibraryManagementSystem.serviceinterfaces;

import com.LibraryManagementSystem.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Integer userId);
    UserDTO updateUser(Integer userId, UserDTO userDTO);
    void deleteUser(Integer userId);

    // New Sign In and Sign Out
    String signIn(String username, String password);
    String signOut(Integer userId);

    String signOut(String username);
}

