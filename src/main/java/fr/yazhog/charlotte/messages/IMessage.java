package fr.yazhog.charlotte.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public abstract class IMessage {

    private String userID, channelID;

    public IMessage(String userID, String channelID) {
        this.userID = userID;
        this.channelID = channelID;
    }

    public abstract void action(MessageReceivedEvent event, User user, Member member);

    public String getUserID() {
        return userID;
    }

    public String getChannelID() {
        return channelID;
    }

    public EmbedBuilder getError(String error) {
        return new EmbedBuilder().setColor(Color.RED).setDescription(error);
    }


}