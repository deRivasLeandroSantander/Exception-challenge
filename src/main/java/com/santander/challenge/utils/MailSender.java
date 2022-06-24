package com.santander.challenge.utils;

import com.santander.challenge.exceptions.MailNotFoundException;
import com.santander.challenge.exceptions.MailServerException;
import org.springframework.mail.MailSendException;

public abstract class MailSender {
    public static void sendMailOfExistence(String email) {
        //This method should send an e-mail asserting that the user already exists in the DB, if the server it's down or the mail address doesn't match any throws an exception
        try {
            sendMail(email);
        } catch (MailServerException | MailNotFoundException | MailSendException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sendMail(String email) throws MailServerException, MailNotFoundException, MailSendException {
        if (! mailServerIsUp()) throw new MailServerException("The mail server is currently down, try again later");
        if (! mailExists(email)) throw new MailNotFoundException("The mail hasn't been sent because the mail address doesn't match any valid address");
        if (! mailReachesAddressee(email)) throw new MailSendException("The mail couldn't reach the addressee");
    }

    public static Boolean mailServerIsUp() {
        //This method returns true if the mail server it's up, false otherwise
        return true;
    }

    public static Boolean mailExists(String email) {
        //This method should check if the mail address exists or not
        return true;
    }

    public static Boolean mailReachesAddressee(String email) {
        //This mail should return true if the mail reaches its addressee, false otherwise
        return true;
    }
}
