package com.repository;

import com.entity.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PersonRepositoryTest {

  @Autowired
  private PersonRepository personRepository;

  @Test
  void appLoad(){


  }

  @Test
  void findByDateOfBirthBetween() {
    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    LocalDate dateOfBirth2 = LocalDate.parse("2011-02-20");
    LocalDate dateOfBirth3 = LocalDate.parse("2022-03-30");

    LocalDate fromDate = LocalDate.parse("2000-03-30");
    LocalDate toDate = LocalDate.parse("2023-03-30");
    Person person = Person.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("+380653883388")
        .build();

    Person person2 = Person.builder()
        .name("test2")
        .surname("test2")
        .email("test3@gmail.com")
        .dateOfBirth(dateOfBirth2)
        .address("test")
        .phoneNumber("+380653883388")
        .build();

    Person person3 = Person.builder()
        .name("test3")
        .surname("test3")
        .email("test3@gmail.com")
        .dateOfBirth(dateOfBirth3)
        .address("test3")
        .phoneNumber("+380653883388")
        .build();

    personRepository.save(person);
    personRepository.save(person2);
    personRepository.save(person3);


    final List<Person> byDateOfBirthBetween = personRepository.findByDateOfBirthBetween(fromDate, toDate);
    assertEquals(2,byDateOfBirthBetween.size());
  }
}
