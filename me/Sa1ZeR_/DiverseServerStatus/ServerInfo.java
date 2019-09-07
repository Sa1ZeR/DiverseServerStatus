package me.Sa1ZeR_.DiverseServerStatus;

import org.bukkit.Bukkit;

public class ServerInfo {

    private long startTime;
    private boolean isEnable;
    private int chunkCount;
    private int online;
    private int maxOnline;
    private long gcMax;
    private long gcFree;
    private long gcTotal;
    private double tpc;
    private long uptime;

    public ServerInfo() {
        this.startTime = System.currentTimeMillis();
        this.isEnable = true;
        this.chunkCount = ServerHelper.getChunks();
        this.online = 0;
        this.maxOnline = Bukkit.getMaxPlayers();
        this.gcMax = Runtime.getRuntime().maxMemory() / 1024L /1024L;
        this.gcFree = Runtime.getRuntime().freeMemory() / 1024L /1024L;
        this.gcTotal = Runtime.getRuntime().totalMemory() / 1024L /1024L;
        this.tpc = ServerHelper.getTPS();
        this.uptime = (System.currentTimeMillis() - startTime) / 1000;
    }

    public int getOnline() {
        return this.online;
    }

    public boolean isEnableServer() {
        return this.isEnable;
    }

    public int getChunkCount() {
        return this.chunkCount;
    }

    public int getMaxOnline() {
        return this.maxOnline;
    }

    public long getGcMax() {
        return this.gcMax;
    }

    public long getGcFree() {
        return this.gcFree;
    }

    public long getGcTotal() {
        return gcTotal;
    }

    public double getTpc() {
        return this.tpc;
    }

    public long getUptime() {
        return this.uptime;
    }

    public void setIsEnable(boolean status) {
        this.isEnable = status;
    }

    public void setChunkCount(int chunkCount) {
        this.chunkCount = chunkCount;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public void setMaxOnline(int maxOnline) {
        this.maxOnline = maxOnline;
    }

    public void setGcMax(long gcMax) {
        this.gcMax = gcMax;
    }

    public void setGcFree(long gcFree) {
        this.gcFree = gcFree;
    }

    public void setGcTotal(long gcTotal) {
        this.gcTotal = gcTotal;
    }

    public void setTpc(double tpc) {
        this.tpc = tpc;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public void updateOnline() {
        if(Bukkit.getOnlinePlayers().size() > 0) {
            this.online = Bukkit.getOnlinePlayers().size();
        }
    }

    public void updateChunkCount() {
        this.chunkCount = ServerHelper.getChunks();
    }

    public void updateGcFree() {
        this.gcFree = Runtime.getRuntime().freeMemory() / 1024L /1024L;
    }

    public void updateGcTotal() {
        this.gcTotal = Runtime.getRuntime().totalMemory() / 1024L /1024L;
    }

    public void updateTpc() {
        this.tpc = ServerHelper.getTPS();
    }

    public void updateUptime() {
        this.uptime = (System.currentTimeMillis() - this.startTime) / 1000L;
    }
}
