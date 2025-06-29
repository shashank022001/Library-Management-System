package com.LibraryManagementSystem.serviceinterfaces;



import com.LibraryManagementSystem.dto.RoleDTO;
import java.util.List;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(Integer roleId);

    RoleDTO updateRole(Integer roleId, RoleDTO roleDTO);

    void deleteRole(Integer roleId);
}

