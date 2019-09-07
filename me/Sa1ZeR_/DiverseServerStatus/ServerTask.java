package me.Sa1ZeR_.DiverseServerStatus;

import org.bukkit.Bukkit;

import static org.bukkit.Bukkit.getLogger;

public class ServerTask implements Runnable {

    @Override
    public void run() {
        ServerStatus.instance.getStatus().updateChunkCount();
        ServerStatus.instance.getStatus().updateOnline();
        ServerStatus.instance.getStatus().updateGcFree();
        ServerStatus.instance.getStatus().updateGcTotal();
        ServerStatus.instance.getStatus().updateTpc();
        ServerStatus.instance.getStatus().updateUptime();
        Bukkit.getScheduler().runTaskAsynchronously(ServerStatus.instance, new Runnable() {
            @Override
            public void run() {
                if (ServerStatus.instance.getStorageManager().isCreate()) {
                    ServerStatus.instance.getStorageManager().saveData(ServerStatus.instance.getStatus());
                } else {
                    ServerStatus.instance.getStorageManager().createServerData(ServerStatus.instance.getStatus());
                }
            }
        });
        if(ServerStatus.instance.getCfg().getBoolean("logger")) {
            getLogger().info("Enable server: " + String.valueOf(ServerStatus.instance.getStatus().isEnableServer()));
            getLogger().info("Chunks: " + String.valueOf(ServerStatus.instance.getStatus().getChunkCount()));
            getLogger().info("Online: " + String.valueOf(ServerStatus.instance.getStatus().getOnline()));
            getLogger().info("Slots: " + String.valueOf(ServerStatus.instance.getStatus().getMaxOnline()));
            getLogger().info("MaxMemory: " + String.valueOf(ServerStatus.instance.getStatus().getGcMax()));
            getLogger().info("Free memory: " + String.valueOf(ServerStatus.instance.getStatus().getGcFree()));
            getLogger().info(String.valueOf("Total Memory: " + ServerStatus.instance.getStatus().getGcTotal()));
            getLogger().info(String.valueOf("TPS: " + ServerStatus.instance.getStatus().getTpc()));
            getLogger().info(String.valueOf("UpTime: " + ServerStatus.instance.getStatus().getUptime()));
        }
    }
}
