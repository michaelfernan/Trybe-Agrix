package com.betrybe.agrix.ebytr.staff.security;

import com.betrybe.agrix.ebytr.staff.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final CustomUserDetailsService userDetailsService;

  /**
   * Instantiates a new Security config.
   *
   * @param userDetailsService the user details service
   */
  @Autowired
  public SecurityConfig(CustomUserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  /**
   * Security filter chain security filter chain.
   *
   * @param http the http
   * @return the security filter chain
   * @throws Exception the exception
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/auth/login").permitAll()
            .requestMatchers("/persons").permitAll()
            .requestMatchers("/farms").hasAnyRole("USER", "MANAGER", "ADMIN")
            .requestMatchers("/crops").hasAnyRole("MANAGER", "ADMIN")
            .requestMatchers("/fertilizers").hasRole("ADMIN")
            .anyRequest().authenticated()
        )
        .addFilterBefore(new JwtAuthenticationFilter(
            userDetailsService), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  /**
   * Password encoder password encoder.
   *
   * @return the password encoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
