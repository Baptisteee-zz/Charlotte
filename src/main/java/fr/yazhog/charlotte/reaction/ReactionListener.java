package fr.yazhog.charlotte.reaction;

import fr.yazhog.charlotte.Charlotte;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class ReactionListener implements EventListener {

    private final Charlotte charlotte;

    public ReactionListener(Charlotte charlotte) {
        this.charlotte = charlotte;
    }

    @Override
    public void onEvent(@Nonnull GenericEvent genericEvent) {
        if (genericEvent instanceof MessageReactionAddEvent) {
            onMessageReceived((MessageReactionAddEvent) genericEvent);
        }
    }

    public void onMessageReceived(MessageReactionAddEvent event) {
        if (event.getUser().getId().equalsIgnoreCase(event.getJDA().getSelfUser().getId())) return;
        List<IReaction> arrayList = charlotte.getEmoteList();
        for (IReaction iReaction : arrayList) {
            if (event.getMessageId().equalsIgnoreCase(iReaction.getMessageID())) {
                iReaction.action(event, event.getUser(), event.getMember());
            }
        }
    }
}
