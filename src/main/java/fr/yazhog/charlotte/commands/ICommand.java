package fr.yazhog.charlotte.commands;

import fr.yazhog.charlotte.utils.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class ICommand {

    private Permission perms;
    private String command;
    private String description = "Aucune description";
    private List<ISubCommand> subCommands = new ArrayList<>();

    public ICommand(String command, Permission perms) {
        this.perms = perms;
        this.command = command;
    }

    public abstract void action(MessageReceivedEvent event, String[] args, User user, Member member);

    public abstract void help(User user, Member member);

    public abstract void help(User user, TextChannel textChannel);

    public Permission getPerms() {
        return perms;
    }

    public ICommand setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
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

    public MessageEmbed embedBuilder(Color color, String text){
        return new EmbedBuilder().setColor(Color.BLACK).setDescription(text).build();
    }

    public MessageEmbed getError(String error) {
        return new EmbedBuilder().setColor(Color.RED).setDescription(error).build();
    }

}