package com.santander.challenge.services;

import com.santander.challenge.dtos.UserDTO;
import com.santander.challenge.dtos.response.ResponseDTO;
import com.santander.challenge.exceptions.*;
import com.santander.challenge.models.User;
import com.santander.challenge.repositories.UserRepository;
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
                sendMailOfExistence("no-response@exceptionexample.com");
                message = "The user already exists";
                throw new UserAlreadyExistsException("The user you're trying to create it's already on the DB");
            }
            else {
                if (! userRepository.save(user)) {
                    throw new UserSaveException("An error occurred when saving the user");
                }
                else {
                    sendSlackToSupportChannel(user);
                    sendMessageToLegacySystem(user);
                    return new ResponseDTO("User created successfully");
                }
            }
        } finally {
            createOperationDataFile(message, user);
        }
    }

    public Boolean checkUserExistence(String email) {
        //This method should check if the user already exists in the DB, if he does then return true, else it returns false
        return false;
    }

    public void sendMailOfExistence(String email) {
        //This method should send an e-mail asserting that the user already exists in the DB, if the server it's down or the mail address doesn't match any throws an exception
        try {
            sendMail(email);
        } catch (MailServerException | MailNotFoundException | MailSendException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMail(String email) throws MailServerException, MailNotFoundException, MailSendException {
        if (! mailServerIsUp()) throw new MailServerException("The mail server is currently down, try again later");
        if (! mailExists(email)) throw new MailNotFoundException("The mail hasn't been sent because the mail address doesn't match any valid address");
        if (! mailReachesAddressee(email)) throw new MailSendException("The mail couldn't reach the addressee");
    }

    public Boolean mailServerIsUp() {
        //This method returns true if the mail server it's up, false otherwise
        return true;
    }

    public Boolean mailExists(String email) {
        //This method should check if the mail address exists or not
        return true;
    }

    public Boolean mailReachesAddressee(String email) {
        //This mail should return true if the mail reaches its addressee, false otherwise
        return true;
    }

    public void sendSlackToSupportChannel(User user) {
        //This method should send a Slack message to the support channel saying that the user was created successfully, if the server it's down or the channel provided doesn't match any throws an exception
        String supportChannel = "#user-support-channel";
        if (! slackServerIsUp()) throw new SlackServerException("The slack server is currently down, try again later");
        if (! slackChannelExists(supportChannel)) throw new SlackChannelNotFoundException("The message hasn't been sent because the slack channel doesn't match any channel");
        if (! slackMessageReachesAddressee(supportChannel, user)) throw new SlackMessageSendException("The Slack message couldn't reach the channel");
    }

    public Boolean slackServerIsUp() {
        //This method should return true if the slack server is up
        return true;
    }

    public Boolean slackChannelExists(String channelName) {
        //This method should return true if the channel provided exists
        return true;
    }

    public Boolean slackMessageReachesAddressee(String channelName, User user) {
        //This method should return true if the message reached its addressee
        return true;
    }

    public void sendMessageToLegacySystem(User user) {
        //This method should send a message to an external Legacy system saying that the user was created successfully, if the server it's down or if it couldn't communicate to it throws an exception
        String server = "ExternalLegacySystem";
        if (! legacyServerIsUp(server)) throw new LegacyServerException("The external legacy server is currently down, try again later");
        if (! legacyMessageReachesAddressee(server, user)) throw new LegacyMessageSendException("The message couldn't reach the external legacy server");
    }

    public Boolean legacyServerIsUp(String server) {
        //This method should return true if the external legacy server is up, false otherwise
        return true;
    }

    public Boolean legacyMessageReachesAddressee(String server, User user) {
        //This method should return true if the message was delivered correctly, false otherwise
        return true;
    }

    public void createOperationDataFile(String message, User user) {
        String path = null;
        String uri = "Some URI";
        try {
            convertUriToPath(path, uri);
        } catch (FileNotFoundException e) {
            //Treat exception
        }
        catch (SecurityException e) {
            //Treat exception
        }
        catch (FileAlreadyOpenException e) {
            //Treat exception
        }
        catch (IllegalArgumentException e) {
            //Treat exception
        }
        try {
            writeFile(user);
        } catch (IOException e) {
            //Treat exception
        }
        catch (SecurityException e) {
            //Treat exception
        }
        catch (UnsupportedOperationException e) {
            //Treat exception
        }
        catch (IllegalArgumentException e) {
            //Treat exception
        }
    }

    public void convertUriToPath(String path, String uri) throws FileNotFoundException {
        //This method should convert the uri to a path, and throws an exception if it can't
    }

    public void writeFile(User user) throws IOException {
        //This method should write a file with the provided user data
    }

}
