package model.criteria;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Results {
    @SerializedName("criteria")
    private Map<Object, Object> criteriaConditions;
    @SerializedName("results")
    private List<Map<String, Object>> results;

    public Results() {
        results = new ArrayList<>();
        criteriaConditions = new HashMap<>();
    }

    public List<Map<String, Object>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, Object>> results) {
        this.results = results;
    }

    public Map<Object, Object> getCriteriaConditions() {
        return criteriaConditions;
    }

    public void setCriteriaConditions(Map<Object, Object> conditions) {
        this.criteriaConditions = conditions;
    }
}
