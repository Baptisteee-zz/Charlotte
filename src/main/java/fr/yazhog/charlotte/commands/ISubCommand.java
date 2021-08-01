package fr.yazhog.charlotte.commands;

import fr.yazhog.charlotte.utils.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.List;

public abstract class ISubCommand {

    private String name;
    private String description = "Aucune description";

    public ISubCommand(String name) {
        this.name = name;
    }

    public abstract void action(MessageReceivedEvent event, String[] args, User user, Member member);

    public String getName() {
        return name;
    }

    public ISubCommand setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
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
