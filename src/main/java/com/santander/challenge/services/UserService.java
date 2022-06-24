package com.santander.challenge.services;

import com.santander.challenge.dtos.UserDTO;
import com.santander.challenge.dtos.response.ResponseDTO;
import com.santander.challenge.exceptions.*;
import com.santander.challenge.models.User;
import com.santander.challenge.repositories.UserRepository;
import com.santander.challenge.utils.FileCreator;
import com.santander.challenge.utils.LegacyMessageSender;
import com.santander.challenge.utils.MailSender;
import com.santander.challenge.utils.SlackMessageSender;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseDTO createUser(UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getPassword());
        String message = "User {user} created successfully";
        try {
            if(checkUserExistence(user.getEmail()) ) {
                MailSender.sendMailOfExistence("no-response@exceptionexample.com");
                message = "The user already exists";
                throw new UserAlreadyExistsException("The user you're trying to create it's already on the DB");
            }
            else {
                if (! userRepository.save(user)) {
                    throw new UserSaveException("An error occurred when saving the user");
                }
                else {
                    SlackMessageSender.sendSlackToSupportChannel(user);
                    LegacyMessageSender.sendMessageToLegacySystem(user);
                    return new ResponseDTO("User created successfully");
                }
            }
        } finally {
            FileCreator.createOperationDataFile(message, user);
        }
    }

    public Boolean checkUserExistence(String email) {
        //This method should check if the user already exists in the DB, if he does then return true, else it returns false
        return false;
    }
}
