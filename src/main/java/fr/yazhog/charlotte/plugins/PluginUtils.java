package fr.yazhog.charlotte.plugins;

import fr.yazhog.charlotte.BCharlotte;
import fr.yazhog.charlotte.Charlotte;
import fr.yazhog.charlotte.commands.ICommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginUtils {

    private Charlotte charlotte;

    public PluginUtils(Charlotte charlotte) {
        this.charlotte = charlotte;
    }

    public void loadPlugins() throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException, URISyntaxException {
        File pFile = new File(new File(Charlotte.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent() + "/plugins/");
        if(!pFile.exists()) pFile.mkdir();
        for (File file : pFile.listFiles()) {
            if (!file.getName().endsWith(".jar")) continue;
            System.out.println("Loading " + file.getName() + "...");
            URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()});
            if (loader.getResourceAsStream("plugin.info") == null) {
                throw new NullPointerException("Jar does not contains plugin.info");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("plugin.info")));
            String mClass = reader.readLine();
            String name = reader.readLine();
            Class<?> jarClass = loader.loadClass(mClass);
            if (!BCharlotte.class.isAssignableFrom(jarClass)) {
                throw new IllegalStateException("Jarfile " + file.getPath() + " isn't a plugin");
            }
            BCharlotte bCharlotte = null;
            try {
                bCharlotte = (BCharlotte) jarClass.getConstructor(Charlotte.class).newInstance(charlotte);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                System.out.println("Error while loading " + name + ", aborting this plugin");
                continue;
            }
            bCharlotte.onEnable();
            charlotte.getPlugins().add(new Plugin(bCharlotte, name, file));
            System.out.println(file.getName() + " loaded");
        }
    }

    public void loadPlugin(File file) throws ClassNotFoundException, IOException, IllegalAccessException, InstantiationException {
        if (!file.getName().endsWith(".jar")) return;
        System.out.println("Loading " + file.getName() + "...");
        URLClassLoader loader = new URLClassLoader(new URL[]{file.toURI().toURL()});
        if (loader.getResourceAsStream("plugin.info") == null) {
            throw new NullPointerException("Jar does not contains plugin.info");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("plugin.info")));
        String mClass = reader.readLine();
        String name = reader.readLine();
        Class<?> jarClass = loader.loadClass(mClass);
        if (!BCharlotte.class.isAssignableFrom(jarClass)) {
            throw new IllegalStateException("Jarfile " + file.getPath() + " isn't a plugin");
        }
        BCharlotte bCharlotte = null;
        try {
            bCharlotte = (BCharlotte) jarClass.getConstructor(Charlotte.class).newInstance(charlotte);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("Error while loading " + name + ", aborting this plugin");
            return;
        }
        bCharlotte.onEnable();
        charlotte.getPlugins().add(new Plugin(bCharlotte, name, file));
        System.out.println(file.getName() + " loaded");
    }

    public void reload() {
        charlotte.getPlugins().forEach(plugin -> {
            plugin.getbCharlotte().onDisable();
        });
        try {
            loadPlugins();
        } catch (ClassNotFoundException | IOException | IllegalAccessException | InstantiationException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void reload(Plugin plugin) {
        plugin.getbCharlotte().onDisable();
        charlotte.getPlugins().remove(plugin);
        try {
            loadPlugin(plugin.getFile());
        } catch (ClassNotFoundException | IllegalAccessException | IOException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
