package com.consoleapp1.repository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DbManager {
    private static DbManager dbManager = new DbManager();
    private static Connection connection;

    private DbManager(){}

    public static DbManager getDbManager(){
        return dbManager;
    }
    public void connectToDatabase(){
        if(connection != null){
            return;
        }
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch(ClassNotFoundException ex) {
            System.out.println("Unable to load class");
            System.exit(1);
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:8080/tasksdb",
                    "postgres",
                    "postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}

