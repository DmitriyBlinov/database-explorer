package model.criteria;

import java.util.Map;

public class MinMaxPurchaseCriterion extends Criterion {
    //Минимальная и максимальная стоимость всех покупок — поиск покупателей, у которых общая стоимость всех покупок за всё время попадает в интервал
    private Double minExpenses;
    private Double maxExpenses;

    public MinMaxPurchaseCriterion() {

    }

    public MinMaxPurchaseCriterion(Double minExpenses, Double maxExpenses) {
        this.minExpenses = minExpenses;
        this.maxExpenses = maxExpenses;
    }

    @Override
    public Criterion checkCriterion(Map<String, Object> criterion) {
        if (criterion != null &&
                criterion.containsKey("minExpenses")
                && criterion.containsKey("maxExpenses")
                && criterion.size() == 2) {
            this.minExpenses = (Double) (criterion.get("minExpenses"));
            this.maxExpenses = (Double) (criterion.get("maxExpenses"));
            return this;
        }
        return null;
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
