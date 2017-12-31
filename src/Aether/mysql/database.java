package Aether.mysql;

import Aether.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class database {

    private static Main plugin; 
    private static Connection conn;

    public database(Main main) {
        setPlugin(main);
        try {
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://mysql8.db4free.net:3307/";
            String dbname = "aether";
            String username = "zero";
            String password = "";
            Class.forName(driver);

            database.conn = DriverManager.getConnection(url + dbname + "?autoReconnect=true&useSSL=false", username, password);
            createTable();

            getPlugin().info("Connected to MySQL: " + dbname.substring(0, 1).toUpperCase() + dbname.substring(1).toLowerCase());

        } catch (Exception e) {
            getPlugin().info("MySQL failed to connect: " + e);
        }
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static void setPlugin(Main plugin) {
        database.plugin = plugin;
    }

    private static void createTable() {
        try {
            PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS playerData(username varchar(255) NOT NULL, display_name varchar(255), kills int, deaths int, last_login varchar(255))");
            create.executeUpdate();

        } catch (Exception e) {
            getPlugin().info("MySQL failed to create table: " + e);
        }
    }
}
