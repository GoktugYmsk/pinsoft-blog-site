package com.pinsoft.blogsite.service;

import com.pinsoft.blogsite.dto.AuthenticationRequest;
import com.pinsoft.blogsite.dto.AuthenticationResponse;
import com.pinsoft.blogsite.dto.RegisterRequest;
import com.pinsoft.blogsite.entity.Role;
import com.pinsoft.blogsite.entity.User;
import com.pinsoft.blogsite.repository.RoleRepository;
import com.pinsoft.blogsite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  @Autowired
  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final JwtService jwtService;

  private final AuthenticationManager authManager;

  public AuthenticationResponse register(RegisterRequest request) throws Exception {
    User user = new User();
    if(request.getUsername().isEmpty()||request.getEmail().isEmpty()){
      throw new Exception("username/email cannot be empty!");
    }else{
      user.setUsername(request.getUsername());
      user.setEmail(request.getEmail());
      user.setPassword(request.getPassword());
      Role role = roleRepository.findByNameEquals("user").get(0);
      user.setRole(role);
      User savedUser = userRepository.save(user);
      var jwtToken = jwtService.generateToken(savedUser);
      return AuthenticationResponse.builder()
        .token(jwtToken).build();
    }
  }
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        request.getUsername(),
        request.getPassword()
      )
    );
    var user =userRepository.findByUsername(request.getUsername());
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
      .token(jwtToken).build();
  }
}
