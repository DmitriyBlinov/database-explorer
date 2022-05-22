package controller.database;

import sun.rmi.runtime.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseWorker {
    private Connection connection = null;
    private String host = "localhost";
    private String port = "5432";
    private String databaseName = "Shop";
    private String username = "postgres";
    private String password = "root";
    private Statement statement;

    public DatabaseWorker() {

    }

    public boolean getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port +
                    "/" + databaseName + "", "" + username + "", "" + password + "");
            statement = connection.createStatement();
            if (connection != null) {
                System.out.println("Connection OK");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connection Failed");
        return false;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}