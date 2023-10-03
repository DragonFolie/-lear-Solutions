package com.service;

import com.entity.Person;
import com.repository.PersonRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Data
public class PersonService {

  private PersonRepository personRepository;


  @Autowired
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public List<Person> findAllPerson() {
    return personRepository.findAll();
  }


  public Person addNewPerson(Person person) {
    return personRepository.save(person);
  }

  public Person updatePerson(Person person) {
    return personRepository.save(person);
  }


  public void deletePersonById(Long id) {
    personRepository.deleteById(id);
  }

  public List<Person> findUsersByBirthDateRange(LocalDate from, LocalDate to) {
    return personRepository.findByDateOfBirthBetween(from, to);
  }


}
