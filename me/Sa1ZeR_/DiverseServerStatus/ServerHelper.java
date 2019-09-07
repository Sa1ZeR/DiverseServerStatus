package me.Sa1ZeR_.DiverseServerStatus;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.List;

public class ServerHelper implements Runnable {

    private static double tps;
    private static long sec;
    private static int ticks;
    private static long currentSec;

    public static int getChunks() {
        int chunk = 0;
        List<World> worlds = Bukkit.getWorlds();
        for (World w : worlds) {
            chunk += w.getLoadedChunks().length;
        }
        return chunk;
    }

    public static double getTPS() {
        return tps;
    }

    public static void disableServer() {
        ServerStatus.instance.getStatus().setIsEnable(false);
        ServerStatus.instance.getStatus().setChunkCount(0);
        ServerStatus.instance.getStatus().setGcFree(0);
        ServerStatus.instance.getStatus().setGcMax(0);
        ServerStatus.instance.getStatus().setGcTotal(0);
        ServerStatus.instance.getStatus().setMaxOnline(0);
        ServerStatus.instance.getStatus().setOnline(0);
        ServerStatus.instance.getStatus().setTpc(0);
        ServerStatus.instance.getStatus().setUptime(0);
        if(ServerStatus.instance.getStorageManager().isCreate()) {
            ServerStatus.instance.getStorageManager().saveData(ServerStatus.instance.getStatus());
        } else {
            ServerStatus.instance.getStorageManager().createServerData(ServerStatus.instance.getStatus());
        }
    }

    @Override
    public void run() {
        sec = (System.currentTimeMillis() / 1000);
        if (currentSec == sec) {
            ticks++;
        } else {
            currentSec = sec;
            tps = (tps == 0 ? ticks : ((tps + ticks) / 2));
            ticks = 0;
        }
    }
}
