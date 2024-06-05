package com.betrybe.agrix.ebytr.staff.service;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type Authentication service.
 */
@Service
public class AuthenticationService {

  private final PersonRepository personRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserDetailsService userDetailsService;
  private final String secretKey = "secret";

  /**
   * Instantiates a new Authentication service.
   *
   * @param personRepository   the person repository
   * @param passwordEncoder    the password encoder
   * @param userDetailsService the user details service
   */
  @Autowired
  public AuthenticationService(
      PersonRepository personRepository,
      PasswordEncoder passwordEncoder,
      UserDetailsService userDetailsService) {
    this.personRepository = personRepository;
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
  }

  /**
   * Generate token string.
   *
   * @param authentication the authentication
   * @return the string
   */
  public String generateToken(Authentication authentication) {
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(authentication.getName())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(
            System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  /**
   * Authenticate string.
   *
   * @param username the username
   * @param password the password
   * @return the string
   */
  public String authenticate(String username, String password) {
    Person person = personRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(password, person.getPassword())) {
      throw new RuntimeException("Invalid credentials");
    }

    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    return generateToken(authenticationToken);
  }
}
