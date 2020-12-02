package fr.yazhog.charlotte.messages;

import fr.yazhog.charlotte.Charlotte;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class IMessageListener implements EventListener {

    private Charlotte charlotte;

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
        ArrayList<IMessage> messages = new ArrayList<>(charlotte.getMessageList());
        if (event.getChannelType() == ChannelType.PRIVATE) {
            for (IMessage iMessage : messages) {
                if (event.getAuthor().getId().equalsIgnoreCase(iMessage.getUserID())) {
                    iMessage.action(event, event.getAuthor(), event.getMember());
                }
            }
        }
    }

}
