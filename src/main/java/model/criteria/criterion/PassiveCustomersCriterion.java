package model.criteria.criterion;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

public class PassiveCustomersCriterion extends Criterion {
    //Число пассивных покупателей — поиск покупателей, купивших меньше всего товаров. Возвращается не более, чем указанное число покупателей.
    @SerializedName("badCustomers")
    private Double badCustomers;

    public PassiveCustomersCriterion() {

    }

    public PassiveCustomersCriterion(Double badCustomers) {
        this.badCustomers = badCustomers;
    }

    public String prepareSearchQuery() {
        String query = "SELECT customers.name, \"lastName\"\n" +
                "FROM customers\n" +
                "JOIN purchases ON customers.id = purchases.\"customerId\"\n" +
                "GROUP BY customers.name, \"lastName\"\n" +
                "ORDER BY COUNT(DISTINCT purchases.\"customerId\")\n" +
                "LIMIT " + badCustomers;
        return query;
    }

    public Map<Object, Object> getCriterionConditions() {
        Map<Object, Object> temp = new HashMap<>();
        temp.put("badCustomers", badCustomers);
        return temp;
    }

    public Double getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(Double badCustomers) {
        this.badCustomers = badCustomers;
    }
}