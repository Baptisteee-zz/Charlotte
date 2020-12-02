package fr.yazhog.charlotte;

import fr.yazhog.charlotte.commands.CommandListener;
import fr.yazhog.charlotte.commands.ICommand;
import fr.yazhog.charlotte.file.FileUtils;
import fr.yazhog.charlotte.messages.IMessage;
import fr.yazhog.charlotte.messages.IMessageListener;
import fr.yazhog.charlotte.emotes.EmoteListener;
import fr.yazhog.charlotte.emotes.IEmote;
import fr.yazhog.charlotte.plugins.Plugin;
import fr.yazhog.charlotte.plugins.PluginUtils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

public class Charlotte {

    private List<IEmote> emoteList = new ArrayList<>();
    private JDA jda;
    private ArrayList<ICommand> commands = new ArrayList<>();
    private List<IMessage> messageList = new ArrayList<>();
    private List<Plugin> plugins = new ArrayList<>();
    private final long startedTime = System.currentTimeMillis();;
    private PluginUtils pluginUtils;
    private final FileUtils fileUtils = new FileUtils();

    public static void main(String[] args) {
        Charlotte charlotte = new Charlotte();
        charlotte.start();
    }

    public void start() {
        pluginUtils = new PluginUtils(this);
        fileUtils.loadFile();
        try {
            jda = JDABuilder.createDefault(fileUtils.getConfig().getString("token")).addEventListeners(
                    new IMessageListener(this), new EmoteListener(this), new CommandListener(this)).build();
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(0);
        }
        try {
            pluginUtils.loadPlugins();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public JDA getJda() {
        return jda;
    }

    public ArrayList<ICommand> getCommands() {
        return commands;
    }

    public Plugin getPluginByName(String name) {
        for (Plugin plugin : plugins) {
            if (plugin.getName().equalsIgnoreCase(name)) {
                return plugin;
            }
        }
        return null;
    }

    public FileUtils getFileUtils() {
        return fileUtils;
    }

    public List<IEmote> getEmoteList() {
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

    public PluginUtils getPluginUtils() {
        return pluginUtils;
    }
}
