package com.pinsoft.blogsite.resolver;

import com.pinsoft.blogsite.entity.Role;
import com.pinsoft.blogsite.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Component
public class RoleResolver {

  @Autowired
  private RoleService roleService;

  @QueryMapping
  public List<Role> getAllRoles() {
    return roleService.getAllRoles();
  }

  @MutationMapping
  public String addRole(@Argument String roleName) {
    return roleService.addRole(roleName);
  }
}
