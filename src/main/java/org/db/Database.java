package org.db;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static String cnct_url_h2;
    private static final String BASE_PART_URL = "jdbc:h2:"+Paths.get("src/main/resources").toAbsolutePath() + "/";
    private Connection cnct;
    private static Database instance;
    public Database(String url) {
        if (cnct_url_h2 == null){
            setCnct_url_h2(url);
        } else if (cnct_url_h2.isEmpty()){
            this.cnct_url_h2 = null;
            setCnct_url_h2(url);
        }
    }

    public static Database getInstance() {
        if (instance == null){
            instance = new Database(cnct_url_h2);
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            this.cnct = DriverManager.getConnection(cnct_url_h2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cnct;
    }

    private void setCnct_url_h2(String url) {
            this.cnct_url_h2 = BASE_PART_URL + url;
    }
}
