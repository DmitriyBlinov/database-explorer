package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectDB {
    private Connection connection = null;
    private String host = "localhost";
    private String port = "5432";
    private String db_name = "Shop";
    private String username = "postgres";
    private String password = "root";
    private Statement statement;

    public ConnectDB() {

    }

    public boolean getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port +
                    "/" + db_name + "", "" + username + "", "" + password + "");
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