package com.pinsoft.blogsite.repository;

import com.pinsoft.blogsite.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
 List<Role> findByNameEquals(String name);
}
