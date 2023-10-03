package com.common.mapper;

import com.entity.Person;
import com.model.dto.PersonDto;

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
    return null;
  }
}
