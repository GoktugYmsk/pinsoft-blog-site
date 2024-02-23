package com.pinsoft.blogsite.service;

import com.pinsoft.blogsite.entity.Role;
import com.pinsoft.blogsite.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

  @Autowired
  private RoleRepository roleRepository;

  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }

  public String addRole(String roleName) {
    if (roleRepository.findByNameEquals(roleName).isEmpty()) {
      Role newRole = new Role();
      newRole.setName(roleName);
      roleRepository.save(newRole);
      return "Role added successfully.";
    } else {
      return "Role with the same name already exists.";
    }
  }
  }
