package com.santander.challenge.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String lastName;
    private String email;
    private String password;
}
