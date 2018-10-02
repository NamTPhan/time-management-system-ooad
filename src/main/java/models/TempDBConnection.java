package models;

import java.sql.Connection;
import java.sql.DriverManager;

/*
Temporary alternative database connection. Will be deleted.
 */

public class TempDBConnection {

    private static final String DB_DEFAULT_ACCOUNT = "root";
    private static final String DB_DEFAULT_PASSWORD = "***";

    public static Connection conn = null;

    public Connection connectDB() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/timemanagement",
                        DB_DEFAULT_ACCOUNT, DB_DEFAULT_PASSWORD);
                return conn;
            } catch (Exception e) {
                return null;
            }
        } else {
            return conn;
        }
    }
}
