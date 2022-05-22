package model.criteria;

import java.util.Map;

public class HowManyBoughtCriterion extends Criterion {
    private String productName;
    private Double minTimes;

    public HowManyBoughtCriterion() {

    }

    public HowManyBoughtCriterion(String productName, Double minTimes) {
        this.productName = productName;
        this.minTimes = minTimes;
    }

    @Override
    public HowManyBoughtCriterion checkCriterion(Map<String, Object> criterion) {
        if (criterion != null &&
                criterion.containsKey("productName")
                && criterion.containsKey("minTimes")
                && criterion.size() == 2) {
            return this;
        }
        return null;
    }

    public String prepareSearchQuery() {
        String query = "SELECT customers.name, \"lastName\"\n" +
                "FROM customers\n" +
                "JOIN purchases ON customers.id = purchases.\"customerId\"\n" +
                "WHERE purchases.\"productName\" = '" + productName + "'" +
                "GROUP BY customers.name, \"lastName\"\n" +
                "HAVING COUNT(purchases.\"productName\")>=" + minTimes;
        return query;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getMinTimes() {
        return minTimes;
    }

    public void setMinTimes(Double minTimes) {
        this.minTimes = minTimes;
    }
}