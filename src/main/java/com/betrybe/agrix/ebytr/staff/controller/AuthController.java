package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.service.AuthenticationService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationService authenticationService;

  /**
   * Instantiates a new Auth controller.
   *
   * @param authenticationService the authentication service
   */
  @Autowired
  public AuthController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  /**
   * Login response entity.
   *
   * @param loginRequest the login request
   * @return the response entity
   */
  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
    String username = loginRequest.get("username");
    String password = loginRequest.get("password");
    try {
      String token = authenticationService.authenticate(username, password);
      return ResponseEntity.ok(Map.of("token", token));
    } catch (RuntimeException e) {
      return ResponseEntity.status(403).body(Map.of("error", e.getMessage()));
    }
  }
}
