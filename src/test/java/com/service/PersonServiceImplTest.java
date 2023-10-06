package com.service;

import com.entity.Person;
import com.repository.PersonRepository;
import com.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

  @Mock
  private PersonRepository personRepository;

  @Captor
  private ArgumentCaptor<Person> emailCaptor;

  private AutoCloseable autoCloseable;
  private PersonServiceImpl personServiceImpl;
  private Person person = new Person();

  @BeforeEach
  public void setUp() throws Exception {
    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    Person person = Person.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phone_number("+380653883388")
        .build();
  }

  @Test
  void verifyThatFindAllPersonInvoke() {

    personServiceImpl.findAllPerson();
    verify(personRepository).findAll();

  }

  @Test
  void noSuchElementExpectedInFindAllPersonMethod() {

    List<Person> list = personServiceImpl.findAllPerson();

    assertEquals(0, list.size());
    verify(personRepository).findAll();


  }


  @Test
  void verifyThatAddNewPersonInvoke() {

    personServiceImpl.createPerson(person);
    verify(personRepository).save(person);

  }


  @Test
  void checkThatWeAddedTheSameElementWhichWeAGiveToRepository() {

    ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
    personServiceImpl.createPerson(person);
    verify(personRepository).save(personArgumentCaptor.capture());


    Person personTest = personArgumentCaptor.getValue();
    assertThat(personTest).isEqualTo(person);
    verify(personRepository).save(any());


  }

  @Test
  void verifyThatUpdatePersonInvoke() {
    personServiceImpl.updatePerson(person);
    verify(personRepository).save(person);
  }

  @Test
  void checkThatWeUpdateHaveTheSameElementWhichWeAGiveToRepository() {

    ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
    personServiceImpl.updatePerson(person);
    verify(personRepository).save(personArgumentCaptor.capture());


    Person personTest = personArgumentCaptor.getValue();
    assertThat(personTest).isEqualTo(person);
    verify(personRepository).save(any());


  }


  @Test
  void verifyThatDeleteByIdPersonByIdInvoke() {
    personServiceImpl.deletePersonById(11L);
    verify(personRepository).deleteById(11L);
  }

  @Test
  void checkThatWeDeleteByIdHaveTheSameElementWhichWeAGiveToRepository() {

    ArgumentCaptor<Person> personArgumentCaptor = ArgumentCaptor.forClass(Person.class);
    personServiceImpl.updatePerson(person);
    verify(personRepository).save(personArgumentCaptor.capture());


    Person personTest = personArgumentCaptor.getValue();
    assertThat(personTest).isEqualTo(person);
    verify(personRepository).save(any());


  }

}