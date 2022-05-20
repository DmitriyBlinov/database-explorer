package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Root {

    @SerializedName("criteria")
    private List<Map<String, ?>> criteria;

    public Root() {

    }

    public Map<String, ?> getCriterion(int index) {
        return criteria.get(index);
    }

    public List<Map<String, ?>> getCriteriaList() {
        return criteria;
    }

    public void setCriteria(int index, Map<String, ?> criteria) {
        this.criteria.set(index, criteria);
    }
}