package data.criteria;

import java.util.Map;

public class PassiveCustomersCriterion extends Criterion{
    //Число пассивных покупателей — поиск покупателей, купивших меньше всего товаров. Возвращается не более, чем указанное число покупателей.
    private Double badCustomers;

    public PassiveCustomersCriterion() {

    }

    public PassiveCustomersCriterion(Double badCustomers) {
        this.badCustomers = badCustomers;
    }

    @Override
    public Criterion checkCriterion(Map<String, Object> criterion) {
        if (criterion != null &&
                criterion.containsKey("badCustomers")
                && criterion.size() == 1) {
            this.badCustomers = (Double) (criterion.get("badCustomers"));
            return this;
        }
        return null;
    }

    public String prepareQuery() {
        String query = "SELECT customers.name, \"lastName\"\n" +
                "FROM customers\n" +
                "JOIN purchases ON customers.id = purchases.\"customerId\"\n" +
                "GROUP BY customers.name, \"lastName\"\n" +
                "ORDER BY COUNT(DISTINCT purchases.\"customerId\")\n" +
                "LIMIT " + badCustomers;
        return query;
    }

    public Double getBadCustomers() {
        return badCustomers;
    }

    public void setBadCustomers(Double badCustomers) {
        this.badCustomers = badCustomers;
    }
}