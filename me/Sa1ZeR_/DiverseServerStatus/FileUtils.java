package me.Sa1ZeR_.DiverseServerStatus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    private File file;
    private YamlConfiguration yml;

    public FileUtils(String name, Plugin plugin) {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        this.file.getParentFile().mkdir();
        if(!this.file.exists()) {
            try {
                this.file.createNewFile();
                plugin.saveResource(name + ".yml", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.yml = YamlConfiguration.loadConfiguration(this.file);
    }

    public FileConfiguration get() {
        return this.yml;
    }
}
