package myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

@EnableScheduling
@SpringBootApplication
@EnableSwagger2
public class Main {
    public static void main(String[] args) {
        String database_name = "storage.sqlite";

        File f = new File(database_name);
        if (!f.exists()) {
            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:" + database_name);
                System.out.println("Opened database successfully");

                stmt = c.createStatement();
                List<String> CreatingDb = Files.readAllLines(Paths.get("src/main/resources/CreateDb.sql"));
                String sql = new String();
                for (int i = 0; i < CreatingDb.size(); i++) {
                    sql = sql.concat(CreatingDb.get(i));
                }
                String sql2 = new String();
                List<String> InsertingData = Files.readAllLines(Paths.get("src/main/resources/InsertData.sql"));
                for (int j = 0; j < InsertingData.size(); j++) {
                    sql2 = sql2.concat(InsertingData.get(j));
                }
                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql2);
                stmt.close();
                c.close();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            System.out.println("Tables created successfully");
        }

        SpringApplication.run(Main.class, args);
    }

}
