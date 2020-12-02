package fr.yazhog.charlotte.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;

public abstract class IMessage {

    private String userID;

    public IMessage(String userID) {
        this.userID = userID;
    }

    public abstract void action(MessageReceivedEvent event, User user, Member member);

    public String getUserID() {
        return userID;
    }

    public EmbedBuilder getError(String error) {
        return new EmbedBuilder().setColor(Color.RED).setDescription(error);
    }


}