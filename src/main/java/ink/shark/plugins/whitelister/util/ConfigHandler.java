package ink.shark.plugins.whitelister.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ConfigHandler {
    private File file;
    private FileConfiguration customFile;
    private String fileName;
    private String fileType;
    private String fullFileName;
    private static ArrayList<ConfigHandler> files = new ArrayList<>();

    public ConfigHandler(String fullFileName) {
        this.fullFileName = fullFileName;
        this.fileName = fullFileName.split(".", 2)[0];
        this.fileType = fullFileName.split(".", 2)[1];

        this.file = new File(Bukkit.getServer().getPluginManager().getPlugin("WhiteLister").getDataFolder(), fullFileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException fileCreationErr) {
                fileCreationErr.printStackTrace();
                Common.log("&cError creating file '" + fullFileName + "':");
                Common.log("&c" + fileCreationErr.getMessage());
            }
        }
        this.customFile = YamlConfiguration.loadConfiguration(file);
        save();
        files.add(this);
    }

    public void addDefault(String path, Object value) {
        customFile.addDefault(path, value);
    }

    public void addDefault(HashMap<String, Object> defaults) {
        for (String path : defaults.keySet()) {
            customFile.addDefault(path, defaults.get(path));
        }
    }

    public Object get(String path) {
        if (customFile.get(path) == null) {
            Common.log("&cError getting config option '" + path + "'");
            return -1;
        }
        return customFile.get(path);
    }

    public FileConfiguration getConfig() {
        return customFile;
    }

    public void save() {
        try {
            customFile.save(file);
        } catch (IOException saveConfigurationErr) {
            saveConfigurationErr.printStackTrace();
            Common.log("&cError saving file '" + fullFileName + "':");
            Common.log("&c" + saveConfigurationErr.getMessage());
        }

    }

    public void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void reloadAllFiles() {
        for (ConfigHandler file : files) {
            file.reload();
        }
    }

    public String getFullFileName() {
        return fullFileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public static ArrayList<ConfigHandler> getAllFiles() {
        return files;
    }

    public static ConfigHandler getFile(String fileName) {
        for (ConfigHandler file : files) {
            if (file.getFullFileName().equals(fileName)) {
                return file;
            }
        }
        return null;
    }
}
