package fr.yazhog.charlotte.reaction;

import java.awt.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;

public abstract class IReaction {

    private final String messageID;

    protected IReaction(String messageID) {
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
