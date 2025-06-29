package com.LibraryManagementSystem.serviceclasses;

import com.LibraryManagementSystem.dto.UserDTO;
import com.LibraryManagementSystem.entity.User;
import com.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.LibraryManagementSystem.repository.UserRepository;
import com.LibraryManagementSystem.serviceinterfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return modelMapper.map(userRepository.save(user), UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Integer userId, UserDTO userDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setUsername(userDTO.getUsername());
        existingUser.setPassword(userDTO.getPassword());

        return modelMapper.map(userRepository.save(existingUser), UserDTO.class);
    }

    @Override
    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

    @Override
    public String signIn(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));

        if (!user.getPassword().equals(password)) {
            throw new ResourceNotFoundException("Invalid username or password");
        }

        // You can enhance this method to generate a JWT/token or track session
        return "Sign-in successful for user: " + username;
    }



    @Override
    public String signOut(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        // Normally, you’d invalidate a session or JWT token here.
        return "Sign-out successful for user: " + user.getUsername();
    }

    @Override
    public String signOut(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for sign-out"));

        // Normally, you’d invalidate a session or JWT token here.
        return "Sign-out successful for user: " + username;
    }
}


