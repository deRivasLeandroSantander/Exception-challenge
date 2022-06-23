package com.santander.challenge.controllers;

import com.santander.challenge.dtos.UserDTO;
import com.santander.challenge.dtos.response.ResponseDTO;
import com.santander.challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    UserService userService;

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createUser(@RequestBody UserDTO user) {
        return userService.createUser(user);
    }
}
