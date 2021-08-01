package fr.yazhog.charlotte.commands;

import fr.yazhog.charlotte.Charlotte;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class CommandListener implements EventListener {

    private final Charlotte charlotte;
    private final CommandHandler commandHandler;

    public CommandListener(Charlotte charlotte) {
        this.charlotte = charlotte;
        this.commandHandler = new CommandHandler(charlotte);
    }

    @Override
    public void onEvent(@Nonnull GenericEvent genericEvent) {
        if (genericEvent instanceof MessageReceivedEvent) {
            onMessageReceived((MessageReceivedEvent) genericEvent);
        }
    }

    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannelType() != ChannelType.TEXT) return;
        if (event.getMessage().getContentDisplay()
                .startsWith(charlotte.getFileUtils().getConfig().getString("prefix")) && !event.getAuthor().getId()
                .equals(event.getJDA().getSelfUser().getId())) {
            commandHandler.handleCommand(event);
        }
    }

}
