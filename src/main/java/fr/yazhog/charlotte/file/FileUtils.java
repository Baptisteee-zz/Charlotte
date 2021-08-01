package fr.yazhog.charlotte.file;

import java.io.IOException;
import org.simpleyaml.configuration.file.YamlFile;
import org.simpleyaml.exceptions.InvalidConfigurationException;

public class FileUtils {

    private YamlFile yamlFile;

    public void loadFile() {
        yamlFile = new YamlFile("config.yml");
        if (!yamlFile.exists()) {
            try {
                yamlFile.createNewFile(true);
                yamlFile.set("token", "insert token here");
                yamlFile.set("prefix", "c!");
                yamlFile.save();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                yamlFile.load();
            }
            catch (InvalidConfigurationException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public YamlFile getConfig() {
        return yamlFile;
    }

}
