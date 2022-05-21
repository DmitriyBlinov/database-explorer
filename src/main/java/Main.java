import controller.ConnectDB;
import controller.JsonParser;
import controller.Operations;
import model.Criteria;

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
        Criteria criteria = parser.parse(inputJson);

        System.out.println(criteria.getCriterion(0));

        //connectDB(root);

        ConnectDB connectDB = new ConnectDB();

        if (!connectDB.getConnection()) {
            System.out.println("Connection was not set properly");
            return;
        }

        //fillTable(connectDB.getStatement());

        Operations operations = new Operations(criteria.getCriteriaList(), connectDB.getStatement());
        performOperations(operationType, operations);
    }

    public static void fillTable(Statement statement) throws SQLException {
        //TODO сделать нормальное заполнение таблицы
        String queryCreateCustomers = "CREATE TABLE IF NOT EXISTS customers(id SERIAL PRIMARY KEY, name varchar(225), \"lastName\" varchar(225));";
        String queryCreateProducts = "CREATE TABLE IF NOT EXISTS products(id SERIAL PRIMARY KEY, name varchar(225), price numeric);";
        String queryCreateOrders = "CREATE TABLE IF NOT EXISTS purchases(\"customerId\" int, \"productName\" varchar(225), \"purchaseDate\" date);";
        statement.executeUpdate(queryCreateCustomers);
        statement.executeUpdate(queryCreateProducts);
        statement.executeUpdate(queryCreateOrders);

        String queryInsertCustomers = "INSERT INTO customers" + "(name, \"lastName\")" + "VALUES ('Ivan', 'Ivanov'), ('Petr', 'Petrov'), ('Boris', 'Borisov')";
        String queryInsertProducts = "INSERT INTO products" + "(name, price)" + "VALUES ('meat', 10.0), ('mineral_water', 5.0), ('cola', 7.0)";
        String queryInsertOrders = "INSERT INTO purchases" + "(\"customerId\", \"productName\", \"purchaseDate\")"
                + "VALUES (1, 'meat', '2022-05-21'), (2, 'mineral_water', '2022-05-21'), (3, 'mineral_water', '2022-05-22'), " +
                "(1, 'cola', '2022-05-21'), (2, 'cola', '2022-05-21'), (3, 'meat', '2022-05-22'), " +
                "(1, 'mineral_water', '2022-05-21'), (2, 'cola', '2022-05-21'), (3, 'cola', '2022-05-22')";
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
}