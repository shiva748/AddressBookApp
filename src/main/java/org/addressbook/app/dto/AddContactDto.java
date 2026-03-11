package org.addressbook.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddContactDto {
    @NotBlank(message = "First Name is required")
    @Pattern(regexp = "^[A-Za-z]{3,20}$", message = "First name must contain only letters (3-20 characters)")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Pattern(regexp = "^[A-Za-z]{3,20}$", message = "Last name must contain only letters (3-20 characters)")
    private String lastName;

    @NotBlank(message = "Address is required")
    @Pattern(regexp = "^[A-Za-z0-9\\s,.-]{5,100}$", message = "Invalid address format")
    private String address;

    @NotBlank(message = "City is required")
    @Pattern(regexp = "^[A-Za-z ]{3,30}$", message = "Invalid city name")
    private String city;

    @NotBlank(message = "State is required")
    @Pattern(regexp = "^[A-Za-z ]{3,30}$", message = "Invalid state name")
    private String state;

    @NotBlank(message = "ZIP code is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "ZIP must be a 6 digit number")
    private String zip;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Invalid Indian phone number")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}