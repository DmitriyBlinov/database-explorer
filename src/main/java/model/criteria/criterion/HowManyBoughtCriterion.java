package model.criteria.criterion;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class HowManyBoughtCriterion extends Criterion {
    @SerializedName("productName")
    private String productName;
    @SerializedName("minTimes")
    private Double minTimes;

    public HowManyBoughtCriterion(String productName, Double minTimes) {
        this.productName = productName;
        this.minTimes = minTimes;
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

    public Map<Object, Object> getCriterionConditions() {
        Map<Object, Object> temp = new HashMap<>();
        temp.put("productName", productName);
        temp.put("minTimes", minTimes);
        return temp;
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