package fr.yazhog.charlotte;

import fr.yazhog.charlotte.commands.CommandListener;
import fr.yazhog.charlotte.commands.ICommand;
import fr.yazhog.charlotte.file.FileUtils;
import fr.yazhog.charlotte.messages.IMessage;
import fr.yazhog.charlotte.messages.IMessageListener;
import fr.yazhog.charlotte.plugins.Plugin;
import fr.yazhog.charlotte.plugins.PluginController;
import fr.yazhog.charlotte.reaction.IReaction;
import fr.yazhog.charlotte.reaction.ReactionListener;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Charlotte {

    private final long startedTime = System.currentTimeMillis();
    private final FileUtils fileUtils = new FileUtils();
    private final List<IReaction> emoteList = new ArrayList<>();

    private final List<ICommand> commands = new ArrayList<>();
    private final List<IMessage> messageList = new ArrayList<>();
    private final List<Plugin> plugins = new ArrayList<>();

    private PluginController pluginController;
    private JDA jda;

    public void start() {
        pluginController = new PluginController(this);
        fileUtils.loadFile();
        try {
            jda = JDABuilder
                    .createDefault(fileUtils.getConfig().getString("token"))
                    .addEventListeners(
                            new IMessageListener(this),
                            new ReactionListener(this),
                            new CommandListener(this))
                    .build();
        }
        catch (LoginException e) {
            e.printStackTrace();
            return;
        }
        pluginController.loadPlugins();
    }

    public JDA getJda() {
        return jda;
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    public Plugin getPluginByName(String name) {
        for (Plugin plugin : plugins) {
            if (plugin.getPluginInfo().getPluginName().equalsIgnoreCase(name)) {
                return plugin;
            }
        }
        return null;
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public List<IReaction> getEmoteList() {
        return emoteList;
    }

    public List<IMessage> getMessageList() {
        return messageList;
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }

    public long getStartedTime() {
        return startedTime;
    }

    public PluginController getPluginUtils() {
        return pluginController;
    }



    public static File getJarLocation() {
        try {
            return new File(
                Charlotte.class
                        .getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .toURI()
                        .getPath()
            );
        }
        catch (URISyntaxException ex) {
            throw new IllegalStateException("Could not resolve jar location", ex);
        }
    }
}
