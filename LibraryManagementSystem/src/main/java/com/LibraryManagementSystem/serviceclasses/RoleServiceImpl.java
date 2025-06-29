package com.LibraryManagementSystem.serviceclasses;



import com.LibraryManagementSystem.dto.RoleDTO;
import com.LibraryManagementSystem.entity.Role;
import com.LibraryManagementSystem.exception.ResourceNotFoundException;
import com.LibraryManagementSystem.repository.RoleRepository;
import com.LibraryManagementSystem.serviceinterfaces.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = modelMapper.map(roleDTO, Role.class);
        Role saved = roleRepository.save(role);
        return modelMapper.map(saved, RoleDTO.class);
    }

    @Override
    public RoleDTO getRoleById(Integer roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + roleId));
        return modelMapper.map(role, RoleDTO.class);
    }

    @Override
    public RoleDTO updateRole(Integer roleId, RoleDTO roleDTO) {
        return null;
    }

    @Override
    public void deleteRole(Integer roleId) {

    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());
    }




}

