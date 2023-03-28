package org.db;

import org.utils.URLSetter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DatabasePopulateService {
    public static void main(String[] args) {
        String url_populate = String.valueOf(new URLSetter().getMap().get("populate_db"));
        String url_init_db = String.valueOf(new URLSetter().getMap().get("init_db"));
        try {
            Connection data = new Database(url_init_db).getInstance().getConnection();
            Statement stment = data.createStatement();

            List<String> allLines = Files.readAllLines(Paths.get("src/main/resources/" + url_populate).toAbsolutePath());
            StringBuilder sb = new StringBuilder();
            String sql_query = "";
            for (String this_line : allLines) {
                if (this_line != null) sb.append(this_line);
            }
            sql_query = String.valueOf(sb);

            stment.executeUpdate(sql_query);
            stment.close();
            data.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
