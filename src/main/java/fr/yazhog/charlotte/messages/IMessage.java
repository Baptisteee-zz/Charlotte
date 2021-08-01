package fr.yazhog.charlotte.messages;

import java.awt.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class IMessage {

    private final String userID;
    private final String channelID;

    protected IMessage(String userID, String channelID) {
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