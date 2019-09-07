package me.Sa1ZeR_.DiverseServerStatus;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerStatus extends JavaPlugin {

    public static ServerStatus instance;

    private FileUtils config;
    private ServerInfo sInfo;
    private MySQL mySQL;
    private StorageManager storageManager;

    public void onEnable() {
        instance = this;
        this.config = new FileUtils("config", this);
        this.sInfo = new ServerInfo();
        this.mySQL = new MySQL();
        this.storageManager = new StorageManager();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ServerHelper(), 20, 0);
        long updateTime = this.getCfg().getLong("updateTime") * 20;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ServerTask(), 0, updateTime);
    }

    public void onDisable() {
        ServerHelper.disableServer();
    }

    public FileConfiguration getCfg() {
        return this.config.get();
    }

    public ServerInfo getStatus() {
        return this.sInfo;
    }

    public MySQL getMySQL() {
        return this.mySQL;
    }

    public StorageManager getStorageManager() {
        return this.storageManager;
    }
}
