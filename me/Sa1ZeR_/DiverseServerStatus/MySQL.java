package me.Sa1ZeR_.DiverseServerStatus;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private String user;
    private String pass;
    private String host;
    private String db;
    private String url;

    public MySQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            this.user = ServerStatus.instance.getCfg().getString("mysql.user");
            this.pass = ServerStatus.instance.getCfg().getString("mysql.password");
            this.host = ServerStatus.instance.getCfg().getString("mysql.host");
            this.db = ServerStatus.instance.getCfg().getString("mysql.database");
            this.url = "jdbc:mysql://" + this.host + "/" + db;
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(this.url, this.user, this.pass);
    }
}
