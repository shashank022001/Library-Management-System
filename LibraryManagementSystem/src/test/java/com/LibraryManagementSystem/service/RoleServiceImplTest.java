package com.LibraryManagementSystem.service;



import com.LibraryManagementSystem.controller.RoleController;
import com.LibraryManagementSystem.dto.RoleDTO;
import com.LibraryManagementSystem.serviceinterfaces.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleControllerTest {

    @InjectMocks
    private RoleController roleController;

    @Mock
    private RoleService roleService;

    private RoleDTO roleDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        roleDTO = new RoleDTO();
        roleDTO.setRoleId(1);
        roleDTO.setName("Admin");
    }



    @Test
    void testGetRoleById() {
        when(roleService.getRoleById(1)).thenReturn(roleDTO);
        ResponseEntity<RoleDTO> response = roleController.getRoleById(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getRoleId());
    }

    @Test
    void testGetAllRoles() {
        when(roleService.getAllRoles()).thenReturn(Arrays.asList(roleDTO));
        List<RoleDTO> roles = roleController.getAllRoles();
        assertEquals(1, roles.size());
    }

    @Test
    void testUpdateRole() {
        when(roleService.updateRole(eq(1), any(RoleDTO.class))).thenReturn(roleDTO);
        ResponseEntity<RoleDTO> response = roleController.updateRole(1, roleDTO);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testDeleteRole() {
        doNothing().when(roleService).deleteRole(1);
        ResponseEntity<String> response = roleController.deleteRole(1);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Role deleted successfully", response.getBody());
    }
}