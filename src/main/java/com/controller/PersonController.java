package com.controller;


import com.entity.Person;
import com.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/person")
public class PersonController {


  private final PersonService personService;
  private final Environment environment;

  @Autowired
  public PersonController(PersonService personService, Environment environment) {
    this.personService = personService;
    this.environment = environment;
  }

  @GetMapping(path = "/")
  @ResponseStatus(HttpStatus.OK)
  public List<Person> findAll() {

    List<Person> list = personService.findAllPerson();
    if (list.isEmpty()) {
      throw new NoSuchElementException("No such person founded in list of persons ");
    }

    return list;

  }


  @PostMapping(path = "/")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<Person> addNewPerson(@Valid @RequestBody Person person) {
    LocalDate currentDate = LocalDate.now();
    int minimumAge = Integer.parseInt(environment.getProperty("person.minimum.age"));
    int age = Period.between(person.getDateOfBirth(), currentDate).getYears();

    if (age < minimumAge) {
      throw new IllegalArgumentException("The user's age is less than " + minimumAge + " years, registration is not possible.");
    }

    return new ResponseEntity<>(personService.addNewPerson(person), HttpStatus.CREATED);
  }


  @PutMapping(path = "/")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person) {
    return new ResponseEntity<>(personService.updatePerson(person), HttpStatus.OK);
  }


    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Person> deletePersonById(@PathVariable("id") Long id) {

        personService.deletePersonById(id);
        return ResponseEntity.ok().build();

    }


  @GetMapping(path = "/search/{from}/{to}")
  public ResponseEntity<List<Person>> findUsersByBirthDateRange(
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
      @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {

    if (from.isAfter(to)) {
      return ResponseEntity.badRequest().body(Collections.emptyList());
    }

    List<Person> users = personService.findUsersByBirthDateRange(from, to);

    if (users.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(users);
  }



}
