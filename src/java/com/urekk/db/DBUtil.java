
package com.urekk.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author urekk
 */
public class DBUtil {
    private static Connection con;
    private static final String CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String USER = "sa";
    private static final String PASSWORD = "123";
    private static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=Book_Store;encrypt=false";
    
    static {

    }
    
    public static Connection getConnection() {
        try {
            Class.forName(CLASS_NAME);
            con = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
            
        } catch (Exception e) {
            System.out.println("Fail to get connection to the server");
            e.printStackTrace();
        }
        return con;
    }
}
