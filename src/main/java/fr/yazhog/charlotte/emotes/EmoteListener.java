package fr.yazhog.charlotte.emotes;

import fr.yazhog.charlotte.Charlotte;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class EmoteListener implements EventListener {

    private Charlotte charlotte;

    public EmoteListener(Charlotte charlotte) {
        this.charlotte = charlotte;
    }

    @Override
    public void onEvent(@Nonnull GenericEvent genericEvent) {
        if (genericEvent instanceof MessageReactionAddEvent) {
            onMessageRecieved((MessageReactionAddEvent) genericEvent);
        }
    }

    public void onMessageRecieved(MessageReactionAddEvent event) {
        if (event.getUser().getId().equalsIgnoreCase(event.getJDA().getSelfUser().getId())) return;
        ArrayList<IEmote> arrayList = new ArrayList<>(charlotte.getEmoteList());
        for (IEmote iEmote : arrayList) {
            if (event.getMessageId().equalsIgnoreCase(iEmote.getMessageID())) {
                iEmote.action(event, event.getUser(), event.getMember());
            }
        }
    }
}
