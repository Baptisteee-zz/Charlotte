package fr.yazhog.charlotte.plugins;

import fr.yazhog.charlotte.BCharlotte;
import java.io.File;

public class Plugin {

    private final BCharlotte bCharlotte;
    private final PluginController.PluginInfo pluginInfo;
    private final File file;

    public Plugin(BCharlotte bCharlotte, File file, PluginController.PluginInfo pluginInfo) {
        this.bCharlotte = bCharlotte;
        this.pluginInfo = pluginInfo;
        this.file = file;
    }

    public BCharlotte getbCharlotte() {
        return bCharlotte;
    }

    public PluginController.PluginInfo getPluginInfo() {
        return pluginInfo;
    }

    public File getFile() {
        return file;
    }
}
