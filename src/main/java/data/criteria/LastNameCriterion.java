package data.criteria;

import java.util.Map;

public class LastNameCriterion extends Criterion {
    //Фамилия — поиск покупателей с этой фамилией
    private String lastName;

    public LastNameCriterion() {

    }

    public LastNameCriterion(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public LastNameCriterion checkCriterion(Map<String, Object> criterion) {
        if (criterion != null &&
                criterion.containsKey("lastName")
                && criterion.size() == 1) {
            this.lastName = criterion.get("lastName").toString();
            return this;
        }
        return null;
    }

    public String prepareQuery() {
        String query = "SELECT customers.name, \"lastName\" " +
                "FROM customers " +
                "WHERE \"lastName\" = '" + lastName + "';";
        return query;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}