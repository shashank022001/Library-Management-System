package com.LibraryManagementSystem.controller;

import com.LibraryManagementSystem.dto.RoleDTO;
import com.LibraryManagementSystem.serviceinterfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(roleService.createRole(roleDTO));
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Integer roleId) {
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable Integer roleId, @RequestBody RoleDTO roleDTO) {
        return ResponseEntity.ok(roleService.updateRole(roleId, roleDTO));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable Integer roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.ok("Role deleted successfully");
    }
}
