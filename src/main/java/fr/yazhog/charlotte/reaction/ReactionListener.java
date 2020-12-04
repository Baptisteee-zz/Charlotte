package fr.yazhog.charlotte.reaction;

import fr.yazhog.charlotte.Charlotte;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class ReactionListener implements EventListener {

    private Charlotte charlotte;

    public ReactionListener(Charlotte charlotte) {
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
        ArrayList<IReaction> arrayList = new ArrayList<>(charlotte.getEmoteList());
        for (IReaction iReaction : arrayList) {
            if (event.getMessageId().equalsIgnoreCase(iReaction.getMessageID())) {
                iReaction.action(event, event.getUser(), event.getMember());
            }
        }
    }
}
