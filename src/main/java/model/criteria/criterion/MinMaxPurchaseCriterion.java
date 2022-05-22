package model.criteria.criterion;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class MinMaxPurchaseCriterion extends Criterion {
    //Минимальная и максимальная стоимость всех покупок — поиск покупателей, у которых общая стоимость всех покупок за всё время попадает в интервал
    @SerializedName("minExpenses")
    private Double minExpenses;
    @SerializedName("maxExpenses")
    private Double maxExpenses;

    public MinMaxPurchaseCriterion() {

    }

    public MinMaxPurchaseCriterion(Double minExpenses, Double maxExpenses) {
        this.minExpenses = minExpenses;
        this.maxExpenses = maxExpenses;
    }

    public String prepareSearchQuery() {
        String query = "SELECT customers.name, \"lastName\"\n" +
                "FROM customers\n" +
                "JOIN purchases ON customers.id = purchases.\"customerId\"\n" +
                "JOIN products ON products.name = purchases.\"productName\"\n" +
                "GROUP BY customers.name, \"lastName\"\n" +
                "HAVING SUM(products.price)>=" + minExpenses + " AND SUM(products.price)<=" + maxExpenses;
        return query;
    }

    public Map<Object, Object> getCriterionConditions() {
        Map<Object, Object> temp = new HashMap<>();
        temp.put("minExpenses", minExpenses);
        temp.put("maxExpenses", maxExpenses);
        return temp;
    }

    public Double getMinExpenses() {
        return minExpenses;
    }

    public void setMinExpenses(Double minExpenses) {
        this.minExpenses = minExpenses;
    }

    public Double getMaxExpenses() {
        return maxExpenses;
    }

    public void setMaxExpenses(Double maxExpenses) {
        this.maxExpenses = maxExpenses;
    }
}
