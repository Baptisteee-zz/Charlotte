package fr.yazhog.charlotte.commands;

import fr.yazhog.charlotte.utils.EmbedBuilder;
import java.awt.*;
import java.util.List;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class ISubCommand {

    private final String name;

    protected ISubCommand(String name) {
        this.name = name;
    }

    public abstract void action(MessageReceivedEvent event, String[] args, User user, Member member);

    public String getName() {
        return name;
    }

    public StringBuilder getAsStringBuilder(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(s -> stringBuilder.append(s).append(" "));
        return stringBuilder;
    }

    public EmbedBuilder getError(String error) {
        return new EmbedBuilder().setColor(Color.RED).setDescription(error);
    }
}
