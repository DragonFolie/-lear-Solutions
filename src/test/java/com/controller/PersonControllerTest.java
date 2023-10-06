package com.controller;

import com.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.dto.PersonDto;
import com.repository.PersonRepository;
import com.service.impl.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
class PersonControllerTest {


  @Autowired
  ApplicationContext context;
  @MockBean
  private PersonRepository personRepository;
  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private PersonServiceImpl personServiceImpl;
  @Autowired
  private Validator validator;
  @Autowired
  private PersonController personController;

  @Test
  void findAll() {
  }

  @Test
  void whenCreatePersonWeShouldGet4xxClientError() throws Exception {
    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    Person person = Person.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("+380653883388")
        .build();


    List<Person> list = new ArrayList<>(Arrays.asList(person, person));

    when(personServiceImpl.findAllPerson()).thenReturn(list);

    mockMvc.perform(MockMvcRequestBuilders
            .post("/person/"))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void savePersonTestShouldReturnTrue() throws Exception {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    Person person = Person.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("+380653883388")
        .build();


    when(personServiceImpl.createPerson(any(Person.class))).thenReturn(person);


    mockMvc.perform(MockMvcRequestBuilders.post("/person/")
            .content(new ObjectMapper().writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andDo(print())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").value("test"))
        .andExpect(jsonPath("$.surname").value("test"))
        .andExpect(jsonPath("$.email").value("test@gmail.com"))
        .andExpect(jsonPath("$.address").value("test"))
        .andExpect(jsonPath("$.phoneNumber").value("+380653883388"))
        .andExpect(jsonPath("$.date_of_birth").value("2000-01-10"));


  }

  @Test
  public void savePersonTestShouldReturnException() throws Exception {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    Person person = Person.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("+380653883388")
        .build();


    when(personServiceImpl.createPerson(any(Person.class))).thenReturn(person);


    mockMvc.perform(MockMvcRequestBuilders.post("/person/")
            .content(new ObjectMapper().writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").value("test"))
        .andExpect(jsonPath("$.surname").value("test"))
        .andExpect(jsonPath("$.email").value("test@gmail.com"))
        .andExpect(jsonPath("$.address").value("test"))
        .andExpect(jsonPath("$.phoneNumber").value("+380653883388"))
        .andExpect(jsonPath("$.date_of_birth").value("2000-01-10"));


  }

  @Test
  public void updatePersonRecordSuccess() throws Exception {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    Person person = Person.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("+380653883388")
        .build();


    Mockito.when(personServiceImpl.updatePerson(person)).thenReturn(person);


    mockMvc.perform(MockMvcRequestBuilders.put("/person/")
            .content(new ObjectMapper().writeValueAsString(person))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());


  }

  @Test
  public void deletePersonByIdSuccess() throws Exception {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    LocalDate dateOfBirth2 = LocalDate.parse("2022-02-20");
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
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth2)
        .address("test")
        .phoneNumber("+380653883388")
        .build();
    List<Person> personList = new ArrayList<>();
    personList.add(person2);
    personList.add(person);

    Mockito.when(personServiceImpl
            .findPersonByBirthDateRange(fromDate,toDate))
            .thenReturn(personList);

    mockMvc.perform(MockMvcRequestBuilders
            .delete("/person/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldCreateMockMvc() {
    assertNotNull(mockMvc);
  }

  @Test
  public void contextLoads() throws Exception {
    assertThat(personController).isNotNull();
  }

  @Test
  public void personSuccessAdded() {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    PersonDto personDto = PersonDto.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("+380653883388")
        .build();

    personController.createPerson(personDto);

    assertEquals(1, personServiceImpl.findAllPerson().size());

  }

  @Test
  public void personListIsEmpty() {

    assertEquals(0, personServiceImpl.findAllPerson().size());

  }

  @Test
  public void personNotSuccessAddedWithNullIdParameter() {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    PersonDto personDto = PersonDto.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("+380653883388")
        .build();

    personController.createPerson(personDto);
    assertTrue(personServiceImpl.findAllPerson().isEmpty());

  }


  @Test
  public void whenGetAllPersonValidationIsOk() {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    PersonDto personDto = PersonDto.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("+380653883388")
        .build();

    personController.createPerson(personDto);
    assertTrue(personServiceImpl.findAllPerson().isEmpty());

    Set<ConstraintViolation<PersonDto>> violations = validator.validate(personDto);
    assertTrue(violations.isEmpty());


  }


  @Test
  public void whenCreatePersonWithWrongValidationEmail() {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    PersonDto personDto = PersonDto.builder()
        .name("test")
        .surname("test")
        .email("WRONG")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("+380653883388")
        .build();

    Set<ConstraintViolation<PersonDto>> violations = validator.validate(personDto);
    assertFalse(violations.isEmpty());


  }


  @Test
  public void whenCreatePersonWithWrongValidationNumber() {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    PersonDto personDto = PersonDto.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("WRONG")
        .build();

    Set<ConstraintViolation<PersonDto>> violations = validator.validate(personDto);
    assertFalse(violations.isEmpty());


  }


  @Test
  public void whenCreatePersonWithNullInName() {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    PersonDto personDto = PersonDto.builder()
        .name(null)
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("WRONG")
        .build();

    Set<ConstraintViolation<PersonDto>> violations = validator.validate(personDto);
    assertFalse(violations.isEmpty());


  }


  @Test
  public void whenCreatePersonWithNullInSurname() {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    PersonDto personDto = PersonDto.builder()
        .name("test")
        .surname(null)
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("WRONG")
        .build();

    Set<ConstraintViolation<PersonDto>> violations = validator.validate(personDto);
    assertFalse(violations.isEmpty());


  }


  @Test
  public void whenCreatePersonWithNullInEmail() {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    PersonDto personDto = PersonDto.builder()
        .name("test")
        .surname("test")
        .email(null)
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber("WRONG")
        .build();

    Set<ConstraintViolation<PersonDto>> violations = validator.validate(personDto);
    assertFalse(violations.isEmpty());


  }


  @Test
  public void whenCreatePersonWithNullInDateOfBirth() {

    PersonDto personDto = PersonDto.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(null)
        .address("test")
        .phoneNumber("WRONG")
        .build();

    Set<ConstraintViolation<PersonDto>> violations = validator.validate(personDto);
    assertFalse(violations.isEmpty());


  }


  @Test
  public void whenCreatePersonWithNullInAddress() {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    PersonDto personDto = PersonDto.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address(null)
        .phoneNumber("WRONG")
        .build();

    Set<ConstraintViolation<PersonDto>> violations = validator.validate(personDto);
    assertFalse(violations.isEmpty());


  }


  @Test
  public void whenCreatePersonWithNullInPhoneNumber() {

    LocalDate dateOfBirth = LocalDate.parse("2000-01-10");
    PersonDto personDto = PersonDto.builder()
        .name("test")
        .surname("test")
        .email("test@gmail.com")
        .dateOfBirth(dateOfBirth)
        .address("test")
        .phoneNumber(null)
        .build();

    Set<ConstraintViolation<PersonDto>> violations = validator.validate(personDto);
    assertFalse(violations.isEmpty());


  }

}