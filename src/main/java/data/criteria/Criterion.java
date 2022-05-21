package data.criteria;

import java.util.Map;

public abstract class Criterion {
    private Map<String, Object> criterion;

    public abstract Criterion checkCriterion(Map<String, Object> criterion);

    public String prepareQuery() {
        return "query";
    }

    public void setCriterion(Map<String, Object> criterion) {
        this.criterion = criterion;
    }

    public Criterion checkCriterionList(Map<String, Object> criterion) {
        if (criterion != null &&
                criterion.containsKey("lastName")
                && criterion.size() == 1) {
            return new LastNameCriterion(criterion.get("lastName").toString());
        } else if (criterion != null &&
                criterion.containsKey("productName")
                && criterion.containsKey("minTimes")
                && criterion.size() == 2) {
            return new HowManyBoughtCriterion(criterion.get("productName").toString(), (Double) (criterion.get("minTimes")));
        } else if (criterion != null &&
                criterion.containsKey("minExpenses")
                && criterion.containsKey("maxExpenses")
                && criterion.size() == 2) {
            return new MinMaxPurchaseCriterion((Double) (criterion.get("minExpenses")), (Double) (criterion.get("maxExpenses")));
        } else if (criterion != null &&
                criterion.containsKey("badCustomers")
                && criterion.size() == 1) {
            return new PassiveCustomersCriterion((Double) (criterion.get("badCustomers")));
        } else {
            return null;
        }
    }
}