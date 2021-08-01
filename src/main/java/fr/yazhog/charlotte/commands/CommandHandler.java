package fr.yazhog.charlotte.commands;

import fr.yazhog.charlotte.Charlotte;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandHandler  {

    private Charlotte charlotte;
    private final List<ICommand> commands = new ArrayList<>();

    public CommandHandler(Charlotte charlotte) {
        this.charlotte = charlotte;
    }

    public void handleCommand(MessageReceivedEvent event) {
        String prefixSplit = event.getMessage().getContentDisplay().replaceFirst(charlotte.getFileUtils().getConfig().getString("prefix"), "");
        String[] split = prefixSplit.split(" ");
        String cmd = split[0];
        String[] args = Arrays.copyOfRange(split, 1, split.length);
        List<ICommand> iCommands = new ArrayList<>(commands);
        for (ICommand iCommand : iCommands) {
            if (iCommand.getCommand().equalsIgnoreCase(cmd)) {
                if(event.getGuild().getMember(event.getJDA().getSelfUser()).hasPermission(Permission.MESSAGE_MANAGE)){
                    event.getMessage().delete().queue();
                } else System.out.println("Hey ! I don't have the permissions to delete messages, that's why I can't delete the message when you're typing a command.");
                iCommand.action(event, args, event.getAuthor(), event.getMember());
                iCommand.getSubCommands().stream().filter(iSubCommand -> args.length >= 1 && iSubCommand.getName().equalsIgnoreCase(args[0])).forEach(iSubCommand ->
                        iSubCommand.action(event, Arrays.copyOfRange(args, 1, args.length), event.getAuthor(), event.getMember()));
            }
        }
    }

    public void registerCommand(ICommand iCommand){
        commands.add(iCommand);
        List<SubcommandData> subcommandData = new ArrayList<>();
        iCommand.getSubCommands().forEach(iSubCommand -> subcommandData.add(new SubcommandData(iSubCommand.getName(), iSubCommand.getDescription())));
        charlotte.getJda().upsertCommand(new CommandData(iCommand.getCommand(), iCommand.getDescription()).addSubcommands(subcommandData)).queue();
    }

}
