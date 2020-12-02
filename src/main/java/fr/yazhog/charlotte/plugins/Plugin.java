package fr.yazhog.charlotte.plugins;

import fr.yazhog.charlotte.BCharlotte;

import java.io.File;

public class Plugin {

    private BCharlotte bCharlotte;
    private String name;
    private File file;

    public Plugin(BCharlotte bCharlotte, String name, File file) {
        this.bCharlotte = bCharlotte;
        this.name = name;
        this.file = file;
    }

    public BCharlotte getbCharlotte() {
        return bCharlotte;
    }

    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }
}
