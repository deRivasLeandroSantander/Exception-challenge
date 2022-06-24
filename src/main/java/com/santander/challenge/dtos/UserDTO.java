package com.santander.challenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "The name cannot be blank")
    @Size(min = 3, max = 15, message = "The name should have at least three and a maximum of fifteen characters")
    private String name;
    @NotBlank(message = "The last name cannot be blank")
    @Size(min = 2, max = 20, message = "The last name should have at least two and a maximum of twenty characters")
    private String lastName;
    @Email(message = "The email has to ve a valid address")
    private String email;
    @NotBlank(message = "The password cannot be blank")
    @Size(min = 8, max = 20, message = "The password should have at least eight and a maximum of twenty characters")
    private String password;
}
