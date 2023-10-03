package com.repository;


import com.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

  List<Person> findByDateOfBirthBetween(LocalDate from, LocalDate to);

}
