package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class StatOperation {
    private Date startDate;
    private Date endDate;
    private Statement statement;

    public StatOperation(Date startDate, Date endDate, Statement statement) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.statement = statement;
    }

    public void doStat() throws SQLException {
        //totalDays - разница между датами
        //массив customers:
        //name - имя, фамилия from customers
        //массив purchases:
        //name - имяПродукта from products
        //expenses - priceПродукта from products
        //totalExpenses - сумма всех покупок за период
        
        String querySumByProduct = "SELECT CONCAT(customers.name, ' ', \"lastName\") as CustomerName, products.name AS ProductName, \n" +
                "SUM(products.price) AS \"totalExpenses\"\n" +
                "FROM customers\n" +
                "LEFT JOIN purchases ON customers.id = purchases.\"customerId\"\n" +
                "LEFT JOIN products ON products.name = purchases.\"productName\"\n" +
                "WHERE \"purchaseDate\" BETWEEN '" + startDate + "' and '" + endDate + "' GROUP BY CustomerName, products.name";

        String querySum = "SELECT SUM(expenses) as totalExpenses, AVG(expenses) as averageExpenses FROM\n" +
                "(SELECT (COUNT(purchases.\"productName\") * products.price) AS expenses FROM purchases\n" +
                "LEFT JOIN products ON purchases.\"productName\" = products.name \n" +
                "WHERE \"purchaseDate\" BETWEEN '" + startDate + "' and '" + endDate + "' GROUP BY products.name, products.price) as sumExpenses";

        String queryTotalSum = "SELECT CONCAT(customers.name, ' ', \"lastName\") as CustomerName,\n" +
                "SUM(products.price) AS \"totalExpenses\"\n" +
                "FROM customers\n" +
                "LEFT JOIN purchases ON customers.id = purchases.\"customerId\"\n" +
                "LEFT JOIN products ON products.name = purchases.\"productName\"\n" +
                "WHERE \"purchaseDate\" BETWEEN '" + startDate + "' and '" + endDate + "' GROUP BY CustomerName";

        ResultSet resultSet = statement.executeQuery(querySumByProduct);
        printResultSet(resultSet);

        resultSet = statement.executeQuery(querySum);
        printResultSet(resultSet);

        resultSet = statement.executeQuery(queryTotalSum);
        printResultSet(resultSet);
    }

    private void printResultSet(ResultSet resultSet) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(metaData.getColumnName(i) + ": " + columnValue);
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}