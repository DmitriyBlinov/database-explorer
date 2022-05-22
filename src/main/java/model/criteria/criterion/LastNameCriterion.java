package model.criteria.criterion;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class LastNameCriterion extends Criterion {
    //Фамилия — поиск покупателей с этой фамилией
    @SerializedName("lastName")
    private String lastName;

    public LastNameCriterion() {

    }

    public LastNameCriterion(String lastName) {
        this.lastName = lastName;
    }

    public String prepareSearchQuery() {
        String query = "SELECT customers.name, \"lastName\" " +
                "FROM customers " +
                "WHERE \"lastName\" = '" + lastName + "';";
        return query;
    }

    @Override
    public Map<Object, Object> getCriterionConditions() {
        Map<Object, Object> temp = new HashMap<>();
        temp.put("lastName", lastName);
        return temp;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}