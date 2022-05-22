package controller.database.operations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import model.customer.Purchase;
import model.customer.Customer;
import model.errors.DateError;
import model.errors.NoDateError;

import java.io.FileWriter;
import java.io.Writer;
import java.time.temporal.ChronoUnit;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Stat {
    @SerializedName("type")
    private final String type = "stat";
    @SerializedName("totalDays")
    private long totalDays;
    @SerializedName("customers")
    private List<Customer> customersList;
    @SerializedName("totalExpenses")
    private int totalExpenses;
    @SerializedName("avgExpenses")
    private double avgExpenses;
    private transient Date startDate;
    private transient Date endDate;
    private transient Statement statement;
    private transient String fileName;
    private transient String message;

    public Stat(Date startDate, Date endDate, Statement statement, String fileName) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.statement = statement;
        this.fileName = fileName;
        this.message = "Statistics is ready! Output file name: " + fileName;
    }

    public void requestStat() throws SQLException {
        if (!checkJsonForErrors()) {
            return;
        }

        String queryProductsSum = "SELECT customers.id, CONCAT(customers.name, ' ', \"lastName\") as CustomerName, products.name AS ProductName, \n" +
                "SUM(products.price) AS \"totalExpenses\"\n" +
                "FROM customers\n" +
                "LEFT JOIN purchases ON customers.id = purchases.\"customerId\"\n" +
                "LEFT JOIN products ON products.name = purchases.\"productName\"\n" +
                "WHERE \"purchaseDate\" BETWEEN '" + startDate + "' and '" + endDate + "' GROUP BY customers.id, CustomerName, products.name";

        String queryCustomersExpenses = "SELECT customers.id, CONCAT(customers.name, ' ', \"lastName\") as CustomerName,\n" +
                "SUM(products.price) AS \"totalExpenses\"\n" +
                "FROM customers\n" +
                "LEFT JOIN purchases ON customers.id = purchases.\"customerId\"\n" +
                "LEFT JOIN products ON products.name = purchases.\"productName\"\n" +
                "WHERE \"purchaseDate\" BETWEEN '" + startDate + "' and '" + endDate + "' GROUP BY customers.id, CustomerName";

        String queryTotalExpenses = "SELECT SUM(expenses) as totalExpenses, AVG(expenses) as avgExpenses FROM\n" +
                "(SELECT (COUNT(purchases.\"productName\") * products.price) AS expenses FROM purchases\n" +
                "LEFT JOIN products ON purchases.\"productName\" = products.name \n" +
                "WHERE \"purchaseDate\" BETWEEN '" + startDate + "' and '" + endDate + "' GROUP BY products.name, products.price) as sumExpenses";

        //информация по покупкам
        ResultSet resultSet = statement.executeQuery(queryProductsSum);
        parseProductsSumResponse(resultSet);

        //информация по totalExpenses для каждого кастомера
        resultSet = statement.executeQuery(queryCustomersExpenses);
        parseCustomersExpensesResponse(resultSet);

        //информация totalExpenses и avgExpenses
        resultSet = statement.executeQuery(queryTotalExpenses);
        parseTotalExpensesResponse(resultSet);

        try {
            totalDays = ChronoUnit.DAYS.between(startDate.toInstant(), endDate.toInstant());
            sortCustomersByExpenses();
            writeToJson(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void parseProductsSumResponse(ResultSet resultSet) {
        try {
            customersList = new ArrayList<>();
            while (resultSet.next()) {
                //создали объект кастомера
                Customer customer = new Customer();

                int id = resultSet.getInt("id");
                customer.setId(id);

                //создали объект с новым товаром
                Purchase purchase = new Purchase();
                purchase.setName(resultSet.getString("productname"));
                purchase.setExpenses(resultSet.getInt("totalexpenses"));

                //проверили есть ли в списке кастомер с этим id
                if (customersList.contains(customer)) {
                    customer = customersList.get(id - 1);
                    customer.addPurchase(purchase);
                } else {
                    List<Purchase> purchases = new ArrayList<>();
                    purchases.add(purchase);
                    customer.setPurchases(purchases);

                    String customerName = resultSet.getString("customername");
                    customer.setName(customerName);

                    customersList.add(customer);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void parseCustomersExpensesResponse(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int totalExpenses = resultSet.getInt("totalExpenses");
                customersList.get(id - 1).setTotalExpenses(totalExpenses);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private void parseTotalExpensesResponse(ResultSet resultSet) {
        try {
            while (resultSet.next()) {
                totalExpenses = resultSet.getInt("totalExpenses");
                avgExpenses = resultSet.getInt("avgExpenses");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private boolean checkJsonForErrors() {
        if (Objects.equals(startDate, null) || Objects.equals(endDate, null)) {
            try {
                NoDateError error = new NoDateError();
                message = "Error! Error details were written to the file: error.json";
                fileName = "error.json";
                writeToJson(error);
                return false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (!startDate.before(endDate)) {
            try {
                DateError error = new DateError();
                message = "Error! Error details were written to the file: error.json";
                fileName = "error.json";
                writeToJson(error);
                return false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    public void writeToJson(Object object) {
        try {
            Writer writer = new FileWriter(fileName);
            Gson gson = new GsonBuilder().create();
            gson.toJson(object, writer);
            System.out.print(message);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sortCustomersByExpenses() {
        customersList.sort(Customer::compareTo);
    }
}