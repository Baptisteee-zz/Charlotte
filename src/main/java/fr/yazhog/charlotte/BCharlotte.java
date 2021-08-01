package fr.yazhog.charlotte;

import net.dv8tion.jda.api.JDA;
import org.simpleyaml.configuration.file.YamlFile;

public abstract class BCharlotte {

    private final Charlotte charlotte;

    public BCharlotte(Charlotte charlotte)
    {
        this.charlotte = charlotte;
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public void saveDefaultConfig(){
        charlotte.saveDefaultConfig();
    }

    public JDA getJda() {
        return charlotte.getJda();
    }

    public YamlFile getConfig(){ return charlotte.getFileUtils().getConfig(); }

    public Charlotte getCharlotte() {
        return charlotte;
    }
}
