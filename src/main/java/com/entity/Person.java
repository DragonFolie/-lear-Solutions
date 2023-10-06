package com.entity;

import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;


/**
 * User class represents a user in the db.
 */
@Entity
@Table(name = "User")
@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

  @Column(name = "name")
  @NotBlank(message = "First name is required")
   String name;

  @Column(name = "surname")
  @Size(min = 2, max = 48, message = "Size must be between 2 and 48 symbols")
  @NotBlank(message = "Please input data")
   String surname;

  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
   String email;

  @Column(name = "date_of_birth")
  @Past(message = "Birth date must be in the past")
   LocalDate dateOfBirth;

  @Column(name = "address")
   String address;

  @Column(name = "phone_number")
  @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number format")
   String phone_number;


}