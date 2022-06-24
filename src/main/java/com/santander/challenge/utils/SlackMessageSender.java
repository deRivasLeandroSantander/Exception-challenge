package com.santander.challenge.utils;

import com.santander.challenge.exceptions.SlackChannelNotFoundException;
import com.santander.challenge.exceptions.SlackMessageSendException;
import com.santander.challenge.exceptions.SlackServerException;
import com.santander.challenge.models.User;

public abstract class SlackMessageSender {
    public static void sendSlackToSupportChannel(User user) {
        //This method should send a Slack message to the support channel saying that the user was created successfully, if the server it's down or the channel provided doesn't match any throws an exception
        String supportChannel = "#user-support-channel";
        if (! slackServerIsUp()) throw new SlackServerException("The slack server is currently down, try again later");
        if (! slackChannelExists(supportChannel)) throw new SlackChannelNotFoundException("The message hasn't been sent because the slack channel doesn't match any channel");
        if (! slackMessageReachesAddressee(supportChannel, user)) throw new SlackMessageSendException("The Slack message couldn't reach the channel");
    }

    public static Boolean slackServerIsUp() {
        //This method should return true if the slack server is up
        return true;
    }

    public static Boolean slackChannelExists(String channelName) {
        //This method should return true if the channel provided exists
        return true;
    }

    public static Boolean slackMessageReachesAddressee(String channelName, User user) {
        //This method should return true if the message reached its addressee
        return true;
    }
}
