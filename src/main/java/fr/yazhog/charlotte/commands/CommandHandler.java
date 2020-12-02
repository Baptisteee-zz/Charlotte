package fr.yazhog.charlotte.commands;

import fr.yazhog.charlotte.Charlotte;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHandler {

    private Charlotte charlotte;

    public CommandHandler(Charlotte charlotte) {
        this.charlotte = charlotte;
    }

    public void handleCommand(MessageReceivedEvent event) {
        String prefixSplit = event.getMessage().getContentDisplay().replaceFirst(charlotte.getFileUtils().getConfig().getString("prefix"), "");
        String[] split = prefixSplit.split(" ");
        String cmd = split[0];
        String[] args = Arrays.copyOfRange(split, 1, split.length);
        List<ICommand> iCommands = new ArrayList<>(charlotte.getCommands());
        for (ICommand iCommand : iCommands) {
            if (iCommand.getCommand().equalsIgnoreCase(cmd)) {
                try {
                    event.getMessage().delete().queue();
                } catch (InsufficientPermissionException e) {
                    System.out.println("Hey ! I don't have the permissions to delete messages, that's why I can't delete the message when you're typing a command.");
                }
                if(!event.getMember().hasPermission(iCommand.getPerms())) return;
                iCommand.action(event, args, event.getAuthor(), event.getMember());
                iCommand.getSubCommands().stream().filter(iSubCommand -> args.length >= 1 && iSubCommand.getName().equalsIgnoreCase(args[0])).forEach(iSubCommand ->
                        iSubCommand.action(event, Arrays.copyOfRange(args, 1, args.length), event.getAuthor(), event.getMember()));
            }
        }
    }

}
