package fr.yazhog.charlotte.emotes;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

import java.awt.*;

public abstract class IEmote {

    private String messageID;

    public IEmote(String messageID) {
        this.messageID = messageID;
    }

    public abstract void action(MessageReactionAddEvent event, User user, Member member);

    public String getMessageID() {
        return messageID;
    }

    public EmbedBuilder getError(String error) {
        return new EmbedBuilder().setColor(Color.RED).setDescription(error);
    }


}
