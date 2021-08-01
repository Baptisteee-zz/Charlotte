package fr.yazhog.charlotte.messages;

import fr.yazhog.charlotte.Charlotte;
import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

public class IMessageListener implements EventListener {

    private final Charlotte charlotte;

    public IMessageListener(Charlotte charlotte) {
        this.charlotte = charlotte;
    }

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (genericEvent instanceof MessageReceivedEvent) {
            messageReceived((MessageReceivedEvent) genericEvent);
        }
    }

    private void messageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().getId().equalsIgnoreCase(event.getJDA().getSelfUser().getId())) return;
        List<IMessage> messages = charlotte.getMessageList();
        if (event.getChannelType() == ChannelType.TEXT) {
            for (IMessage iMessage : messages) {
                if (event.getAuthor().getId().equalsIgnoreCase(iMessage.getUserID()) && event.getTextChannel().getId()
                        .equalsIgnoreCase(iMessage.getChannelID())) {
                    iMessage.action(event, event.getAuthor(), event.getMember());
                }
            }
        }
    }

}
