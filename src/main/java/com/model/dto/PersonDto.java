package com.model.dto;

import com.common.mapper.ApplicationConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;


/**
 * Incoming DTO to represent {@link com.entity.Person}.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDto {

  @Size(min = ApplicationConstants.DataValidation.MIN_SIZE_OF_NAME,
      max = ApplicationConstants.DataValidation.MAX_SIZE_OF_NAME)
  @Column(name = "name")
  @NotBlank(message = "First name is required")
  private String name;

  @Size(min = ApplicationConstants.DataValidation.MIN_SIZE_OF_SURNAME,
      max = ApplicationConstants.DataValidation.MAX_SIZE_OF_SURNAME)
  @Column(name = "surname")
  @Size(min = 2, max = 48, message = "Size must be between 2 and 48 symbols")
  @NotBlank(message = "Please input data")
  private String surname;


  @Size(min = ApplicationConstants.DataValidation.MIN_SIZE_OF_EMAIL,
      max = ApplicationConstants.DataValidation.MAX_SIZE_OF_EMAIL)
  @NotBlank(message = "First name is required")
  @NotEmpty
  @Email(message = "Invalid email format")
  String email;


  @Size(min = ApplicationConstants.DataValidation.MIN_SIZE_OF_DATE_OF_BIRTH,
      max = ApplicationConstants.DataValidation.MAX_SIZE_OF_DATE_OF_BIRTH)
  @Column(name = "date_of_birth")
  @Past(message = "Birth date must be in the past")
  private LocalDate dateOfBirth;


  @Size(min = ApplicationConstants.DataValidation.MIN_SIZE_OF_ADDRESS,
       max = ApplicationConstants.DataValidation.MAX_SIZE_OF_ADDRESS)
  @Column(name = "address")
  private String address;


  @Column(name = "phone_number")
  @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number format")
  private String phone_number;
  
}
