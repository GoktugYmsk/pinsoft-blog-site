package com.pinsoft.blogsite.resolver;

import com.pinsoft.blogsite.dto.AuthenticationResponse;
import com.pinsoft.blogsite.dto.RegisterRequest;
import com.pinsoft.blogsite.entity.User;
import com.pinsoft.blogsite.service.AuthenticationService;
import com.pinsoft.blogsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Component
public class UserResolver {

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationService authenticationService;

  @QueryMapping
  public List<User> getAll() {
    return userService.getAll();
  }

  @MutationMapping
  public ResponseEntity<AuthenticationResponse> register(
    @Argument RegisterRequest registerRequest
  ) throws Exception {
    return ResponseEntity.ok(authenticationService.register(registerRequest));
  }

  @MutationMapping
  public User addPhoto(@Argument String username, @Argument String base64img) {
    User user = userService.findByUsername(username);
    if (user == null) {
      throw new RuntimeException("User not found with username: " + username);
    }

    user.setBase64img(base64img);
    userService.save(user);

    return user;
  }


}
