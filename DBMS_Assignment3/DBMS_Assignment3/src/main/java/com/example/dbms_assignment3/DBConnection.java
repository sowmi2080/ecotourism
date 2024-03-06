package com.example.dbms_assignment3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnection {
    public static Connection connection=null;
    public static Statement statement=null;

    public static void Connn() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/ecotourism",
                    "root",
                    ""
            );
            statement = connection.createStatement();
            if (connection != null) {
                System.out.println("Success db connection");
            } else {
                System.out.println("Failed db connection");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
