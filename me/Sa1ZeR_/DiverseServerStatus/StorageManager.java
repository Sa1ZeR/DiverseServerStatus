package me.Sa1ZeR_.DiverseServerStatus;

import org.bukkit.Bukkit;

import java.sql.*;

public class StorageManager {

    private Connection connection;

    public StorageManager() {
        try {
            this.connection = ServerStatus.instance.getMySQL().getConnection();
            prepareDB();
        } catch (SQLException e) {
            Bukkit.getLogger().severe("Can't connect to MySQL \n" + e );
            ServerStatus.instance.getServer().shutdown();
        }
    }

    public void prepareDB() {
        try {
            Statement statement = this.connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS servers_status (`srv_name` TEXT NOT NULL, `enable` BOOLEAN NOT NULL, `chunks` SMALLINT NOT NULL, `online` SMALLINT NOT NULL, `maxOnline` SMALLINT NOT NULL, `gcMax` LONG NOT NULL, `gcFree` LONG NOT NULL, `gcTotal` LONG NOT NULL, `tps` TINYINT NOT NULL, `uptime` LONG NOT NULL);");
            statement.close();
        } catch (SQLException e) {
            Bukkit.getLogger().severe("MySQL error while preparing DB \n" + e );
        }
    }

    public boolean isCreate() {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM servers_status");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("srv_name");
                if (name.equalsIgnoreCase(ServerStatus.instance.getCfg().getString("serverName"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createServerData(ServerInfo srvInfo) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("INSERT INTO servers_status (srv_name, enable, chunks, online, maxOnline, gcMax, gcFree, gcTotal, tps, uptime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            String srvName = ServerStatus.instance.getCfg().getString("serverName");
            ps.setString(1, srvName);
            ps.setBoolean(2, srvInfo.isEnableServer());
            ps.setInt(3, srvInfo.getChunkCount());
            ps.setInt(4, srvInfo.getOnline());
            ps.setInt(5, srvInfo.getMaxOnline());
            ps.setLong(6, srvInfo.getGcMax());
            ps.setLong(7, srvInfo.getGcFree());
            ps.setLong(8, srvInfo.getGcTotal());
            ps.setDouble(9, srvInfo.getTpc());
            ps.setLong(10, srvInfo.getUptime());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveData(ServerInfo srvInfo) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("UPDATE servers_status SET enable=?, chunks=?, online=?, maxOnline=?, gcMax=?, gcFree=?, gcTotal=?, tps=?, uptime=? WHERE srv_name=?");
            String srvName = ServerStatus.instance.getCfg().getString("serverName");
            ps.setBoolean(1, srvInfo.isEnableServer());
            ps.setInt(2, srvInfo.getChunkCount());
            ps.setInt(3, srvInfo.getOnline());
            ps.setInt(4, srvInfo.getMaxOnline());
            ps.setLong(5, srvInfo.getGcMax());
            ps.setLong(6, srvInfo.getGcFree());
            ps.setLong(7, srvInfo.getGcTotal());
            ps.setDouble(8, srvInfo.getTpc());
            ps.setLong(9, srvInfo.getUptime());
            ps.setString(10, srvName);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
