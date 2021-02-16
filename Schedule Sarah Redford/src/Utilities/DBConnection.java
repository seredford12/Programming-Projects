package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection
{
    //JDBC connection parts
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String IPAddress = "//3.227.166.251/U05wM8";


    //jdbc url
    private static final String jdbcURL = protocol + vendorName + IPAddress;

    // Connection to mysql jdbc driver
    private static final String MYSQLJDBCDRIVER  = "com.mysql.cj.jdbc.Driver";
    private static Connection conn = null;

    //username reference
    private static final String username = "U05wM8";

    //password reference
    private static String password = "53688629384";

    public static Connection startConnection()
    {
        try {
            Class.forName(MYSQLJDBCDRIVER);
            conn = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection Successful!");
            }
        catch (ClassNotFoundException | SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return conn;

    }

    //connection to close DB
    public static void closeConnection() throws SQLException {
        conn.close();
        System.out.println("Connection Closed Successfully!");
    }

    public static Connection getConn()
    {
        return conn;
    }



}
