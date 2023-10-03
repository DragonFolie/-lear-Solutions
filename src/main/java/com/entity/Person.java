package com.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "User")
@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  @NotBlank(message = "First name is required")
  private String name;

  @Column(name = "surname")
  @Size(min = 2, max = 48, message = "Size must be between 2 and 48 symbols")
  @NotBlank(message = "Please input data")
  private String surname;

  @NotBlank(message = "Email is required")
  @Email(message = "Invalid email format")
  private String email;

  @Column(name = "date_of_birth")
  @Past(message = "Birth date must be in the past")
  private LocalDate dateOfBirth;

  @Column(name = "address")
  private String address;

  @Column(name = "phone_number")
  @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number format")
  private String phone_number;


}