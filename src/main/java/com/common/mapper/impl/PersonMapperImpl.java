package com.common.mapper.impl;

import com.common.mapper.PersonMapper;
import com.entity.Person;
import com.model.dto.PersonDto;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;


@Component
public class PersonMapperImpl implements PersonMapper {

  @Override
  public Person personDtoToPerson(PersonDto personDto) {
    if (personDto == null) {
      return null;
    }

    Person.PersonBuilder person = Person.builder();

    person.name(personDto.getName());
    person.surname(personDto.getSurname());
    person.email(personDto.getEmail());
    person.dateOfBirth(personDto.getDateOfBirth());
    person.address(personDto.getAddress());
    person.phone_number(personDto.getPhone_number());

    return person.build();
  }

  @Override
  public PersonDto personToPersonDto(Person person) {

    if (person == null) {
      return null;
    }

    PersonDto.PersonDtoBuilder personDto = PersonDto.builder();

    personDto.name(person.getName());
    personDto.surname(person.getSurname());
    personDto.email(person.getEmail());
    personDto.dateOfBirth(person.getDateOfBirth());
    personDto.address(person.getAddress());
    personDto.phone_number(person.getPhone_number());

    return personDto.build();
  }
}
