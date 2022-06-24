package com.santander.challenge.utils;

import com.santander.challenge.exceptions.LegacyMessageSendException;
import com.santander.challenge.exceptions.LegacyServerException;
import com.santander.challenge.models.User;

public abstract class LegacyMessageSender {
    public static void sendMessageToLegacySystem(User user) {
        //This method should send a message to an external Legacy system saying that the user was created successfully, if the server it's down or if it couldn't communicate to it throws an exception
        String server = "ExternalLegacySystem";
        if (! legacyServerIsUp(server)) throw new LegacyServerException("The external legacy server is currently down, try again later");
        if (! legacyMessageReachesAddressee(server, user)) throw new LegacyMessageSendException("The message couldn't reach the external legacy server");
    }

    public static Boolean legacyServerIsUp(String server) {
        //This method should return true if the external legacy server is up, false otherwise
        return true;
    }

    public static Boolean legacyMessageReachesAddressee(String server, User user) {
        //This method should return true if the message was delivered correctly, false otherwise
        return true;
    }
}

