package com.pinsoft.blogsite.repository;

import com.pinsoft.blogsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u.id, u.base64img, u.email, u.password, u.role, u.username FROM User u WHERE u.username = :username")
  User findByUsername(@Param("username") String username);
}
