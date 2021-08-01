package fr.yazhog.charlotte.commands;

import fr.yazhog.charlotte.utils.EmbedBuilder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class ICommand {

    private final Permission perms;
    private final String command;
    private final List<ISubCommand> subCommands = new ArrayList<>();

    protected ICommand(String command, Permission perms) {
        this.perms = perms;
        this.command = command;
    }

    public abstract void action(MessageReceivedEvent event, String[] args, User user, Member member);

    public abstract void help(User user, Member member);

    public abstract void help(User user, TextChannel textChannel);

    public Permission getPerms() {
        return perms;
    }

    public String getCommand() {
        return command;
    }

    public void addSubCommand(ISubCommand subCommand) {
        this.subCommands.add(subCommand);
    }

    public List<ISubCommand> getSubCommands() {
        return subCommands;
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