package fr.yazhog.charlotte.commands;

import fr.yazhog.charlotte.Charlotte;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import javax.annotation.Nonnull;

public class CommandListener implements EventListener {

    private Charlotte charlotte;
    private CommandHandler commandHandler;

    public CommandListener(Charlotte charlotte) {
        this.charlotte = charlotte;
        this.commandHandler = new CommandHandler(charlotte);
    }

    @Override
    public void onEvent(@Nonnull GenericEvent genericEvent) {
        if (genericEvent instanceof MessageReceivedEvent) {
            onMessageRecieved((MessageReceivedEvent) genericEvent);
        }
    }

    public void onMessageRecieved(MessageReceivedEvent event) {
        if (event.getChannelType() != ChannelType.TEXT) return;
        if (event.getMessage().getContentDisplay().startsWith(charlotte.getFileUtils().getConfig().getString("prefix")) && !event.getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            commandHandler.handleCommand(event);
        }
    }

}
