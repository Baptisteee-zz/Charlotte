package fr.yazhog.charlotte.plugins;

import fr.yazhog.charlotte.BCharlotte;
import fr.yazhog.charlotte.Charlotte;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginController {

    private final Charlotte charlotte;

    public PluginController(Charlotte charlotte) {
        this.charlotte = charlotte;
    }

    public void loadPlugins() {
        File pluginsFolder = new File(Charlotte.getJarLocation().getParent() + "/plugins/");

        if (!pluginsFolder.exists()) pluginsFolder.mkdir();

        for (File file : pluginsFolder.listFiles()) {
            loadPlugin(file);
        }
    }

    private Class<?> loadMainClass(ClassLoader loader, String className, String filePath) {
        try {
            Class<?> mainClass = loader.loadClass(className);
            if (!BCharlotte.class.isAssignableFrom(mainClass)) {
                throw new IllegalStateException("Jarfile " + filePath + " isn't a plugin");
            }
            return mainClass;
        }
        catch (ClassNotFoundException e) {
            return null;
        }
    }

    private PluginInfo loadPluginInfo(ClassLoader loader, String jarPath) {
        InputStream pluginInfo = loader.getResourceAsStream("plugin.info");
        if (pluginInfo == null) {
            throw new IllegalStateException("Jar does not contains plugin.info");
        }

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(pluginInfo))) {
            String mainClassName = reader.readLine();
            String pluginName = reader.readLine();

            return new PluginInfo(mainClassName, pluginName, jarPath);
        }
        catch (IOException ex) {
            return null;
        }
    }


    private ClassLoader loadClassLoader(File file) {
        try {
            return new URLClassLoader(new URL[]{file.toURI().toURL()});
        }
        catch (MalformedURLException ex) {
            throw new IllegalStateException("Could not load classloader for file " + file.getName());
        }
    }

    public void loadPlugin(File file) {
        if (!file.getName().endsWith(".jar"))
                return;

        System.out.println("Loading " + file.getName() + "...");
        ClassLoader loader = loadClassLoader(file);

        String jarPath = file.getPath();
        PluginInfo pluginInfo = loadPluginInfo(loader, jarPath);

        if (pluginInfo == null) {
            System.out.println("Plugin info not found for " + jarPath);
            return;
        }

        Class<?> mainClass = loadMainClass(loader, pluginInfo.getMainClassName(), jarPath);

        if (mainClass == null) {
            System.out.println("Main class " + pluginInfo.getMainClassName() + "not found");
            return;
        }

        BCharlotte bCharlotte;

        try {
            bCharlotte = (BCharlotte) mainClass.getConstructor(Charlotte.class).newInstance(charlotte);
        }
        catch (ReflectiveOperationException e) {
            e.printStackTrace();
            System.out.println("Error while loading " + pluginInfo.getPluginName() + ", aborting this plugin");
            return;
        }

        bCharlotte.onEnable();
        charlotte.getPlugins().add(new Plugin(bCharlotte, file, pluginInfo));
        System.out.println(file.getName() + " loaded");
    }

    public void reload() {
        charlotte.getPlugins().forEach(plugin -> plugin.getbCharlotte().onDisable());
        loadPlugins();
    }

    public void reload(Plugin plugin) {
        plugin.getbCharlotte().onDisable();
        loadPlugin(plugin.getFile());
    }

    public static class PluginInfo {
        private final String mainClassName;
        private final String pluginName;
        private final String filePath;

        private PluginInfo(String mainClassName, String pluginName, String filePath) {
            this.mainClassName = mainClassName;
            this.pluginName = pluginName;
            this.filePath = filePath;
        }

        public String getMainClassName() {
            return mainClassName;
        }

        public String getPluginName() {
            return pluginName;
        }

        public String getFilePath() {
            return filePath;
        }
    }
}
