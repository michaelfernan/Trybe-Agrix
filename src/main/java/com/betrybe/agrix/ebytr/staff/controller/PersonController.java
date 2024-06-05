package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;
  private final PasswordEncoder passwordEncoder;

  /**
   * Instantiates a new Person controller.
   *
   * @param personService   the person service
   * @param passwordEncoder the password encoder
   */
  @Autowired
  public PersonController(PersonService personService, PasswordEncoder passwordEncoder) {
    this.personService = personService;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Create person response entity.
   *
   * @param person the person
   * @return the response entity
   */
  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<PersonResponse> createPerson(@RequestBody Person person) {
    person.setPassword(passwordEncoder.encode(person.getPassword()));
    Person savedPerson = personService.create(person);
    return new ResponseEntity<>(new PersonResponse(savedPerson), HttpStatus.CREATED);
  }

  private static class PersonResponse {
    private Long id;
    private String username;
    private Role role;

    /**
     * Instantiates a new Person response.
     *
     * @param person the person
     */
    public PersonResponse(Person person) {
      this.id = person.getId();
      this.username = person.getUsername();
      this.role = person.getRole();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
      return id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
      return username;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
      return role;
    }
  }
}
