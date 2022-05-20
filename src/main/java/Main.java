import controller.ConnectDB;
import controller.JsonParser;
import controller.Operations;
import model.Root;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));
        if (arguments.size() != 3) {
            System.out.printf("Incorrect number of arguments: %s" + ". Expected number of arguments: 3%n", arguments.size());
            //TODO вернуть в самом конце return
            arguments.add("search");
            arguments.add("criteria.json");
            arguments.add("output.json");
            //return;
        }
        String operationType = arguments.get(0);
        String inputJson = arguments.get(1);
        String outputJson = arguments.get(2);
        System.out.printf("Operation: %s" + ", Input JSON: %s" +", Output JSON: %s%n", operationType, inputJson, outputJson);

        JsonParser parser = new JsonParser();
        Root root = parser.parse(inputJson);

        System.out.println(root.getCriterion(0));

        //connectDB(root);

        ConnectDB connectDB = new ConnectDB();

        if (!connectDB.getConnection()) {
            System.out.println("Connection was not set properly");
            return;
        }

        fillTable(connectDB.getStatement());

        Operations operations = new Operations(root.getCriteriaList(), connectDB.getStatement());
        performOperations(operationType, operations);
    }

    public static void fillTable(Statement statement) throws SQLException {
        //TODO сделать нормальное заполнение таблицы
        String queryCreateCustomers = "CREATE TABLE IF NOT EXISTS customers(id SERIAL PRIMARY KEY, name varchar, \"lastName\" varchar);";
        String queryCreateProducts = "CREATE TABLE IF NOT EXISTS products(id SERIAL PRIMARY KEY, name varchar, price numeric);";
        String queryCreateOrders = "CREATE TABLE IF NOT EXISTS purchases(\"customerId\" int, \"productId\" int, \"purchaseDate\" date);";
        statement.executeUpdate(queryCreateCustomers);
        statement.executeUpdate(queryCreateProducts);
        statement.executeUpdate(queryCreateOrders);

        String queryInsertCustomers = "INSERT INTO customers" + "(name, \"lastName\")" + "VALUES ('Ivan', 'Ivanov'), ('Petr', 'Petrov'), ('Boris', 'Borisov')";
        String queryInsertProducts = "INSERT INTO products" + "(name, price)" + "VALUES ('meat', 10.0), ('mineral_water', 5.0), ('cola', 7.0)";
        String queryInsertOrders = "INSERT INTO purchases" + "(\"customerId\", \"productId\", \"purchaseDate\")"
                + "VALUES (0, 0, '2022-05-21'), (1, 1, '2022-05-21'), (2, 1, '2022-05-22'), " +
                "(0, 2, '2022-05-21'), (1, 2, '2022-05-21'), (2, 0, '2022-05-22'), " +
                "(0, 1, '2022-05-21'), (1, 2, '2022-05-21'), (2, 2, '2022-05-22')";
        statement.executeUpdate(queryInsertCustomers);
        statement.executeUpdate(queryInsertProducts);
        statement.executeUpdate(queryInsertOrders);
    }

    public static void performOperations(String operationType, Operations operations) {
        switch (operationType) {
            case ("search"):
                try {
                    operations.doSearch();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case ("stat"):
                try {
                    operations.doStat();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    /*public static void connectDB(model.Root root) {
        Connection connection = null;
        String host = "localhost";
        String port = "5432";
        String db_name = "Shop";
        String username = "postgres";
        String password = "root";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "", "" + username + "", "" + password + "");
            if (connection != null) {
                System.out.println("Connection OK");
                Statement statement = connection.createStatement();

                fillTable(statement);

                controller.Operations operations = new controller.Operations(root.getCriteriaList(), statement);
                operations.doSearch();
                //ResultSet resultSet = statement.executeQuery(query);
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}