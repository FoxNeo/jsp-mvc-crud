package de.caceres.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/employeedirectory?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // Never commit a real username or dont write as String
    private static final String USERNAME = "[YOUR USER HERE]";

    // Never commit a real password or dont write as String
    private static final String PASSWORD = "[YOUR DB PASSWORD]";

    private static Connection connection = null;

    public static Connection openConnection() {
        if (connection != null) {
            return connection;
        } else {

            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Success connected to database");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return connection;
        }
    }
}
