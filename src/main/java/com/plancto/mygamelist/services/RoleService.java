package com.plancto.mygamelist.services;

import com.plancto.mygamelist.dtos.RoleDTO;
import com.plancto.mygamelist.enums.RoleName;
import com.plancto.mygamelist.models.exceptions.ResourceNotFoundException;
import com.plancto.mygamelist.models.user.RoleModel;
import com.plancto.mygamelist.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    /**
     * Method to get all roles
     * @return List of roles
     */
    public List<RoleDTO> getAllRoles() {
        List<RoleModel> roles = roleRepository.findAll();

        return roles.stream().map(role -> new ModelMapper().map(role, RoleDTO.class)).collect(Collectors.toList());
    }

    /**
     * method to get a role by id
     * @param id
     * @return Optional.of(roleDTO)
     */
    public Optional<RoleDTO> getRoleById(UUID id) {
        Optional<RoleModel> role = roleRepository.findById(id);
        if(role.isEmpty()) throw new ResourceNotFoundException("Role with id:" + id + "not found!");
        RoleDTO roleDTO = new ModelMapper().map(role.get(),RoleDTO.class);
        return Optional.of(roleDTO);
    }

    /**
     * method to get a role by name
     * @param roleName
     * @return Optional.of(roleDTO);
     */
    public Optional<RoleDTO> getRoleByRoleName(RoleName roleName) {
        Optional<RoleModel> role = roleRepository.findByRoleName(roleName);
        if(role.isEmpty()) throw new ResourceNotFoundException("Role with name:" + roleName + "not found!");
        RoleDTO roleDTO = new ModelMapper().map(role.get(),RoleDTO.class);
        return Optional.of(roleDTO);
    }
}
