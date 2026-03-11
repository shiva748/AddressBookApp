package org.addressbook.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressBookDto {
    @NotBlank(message = "Address Book name cannot be empty.")
    @Pattern(regexp = "^[A-Za-z]{3,20}$", message = "Address Book name must contain only letters (3-20 characters)")
    private String name;
}
