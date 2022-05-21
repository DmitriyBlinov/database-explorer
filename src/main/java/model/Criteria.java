package model;

import com.google.gson.annotations.SerializedName;
import data.criteria.Criterion;

import java.util.List;
import java.util.Map;

public class Criteria {

    @SerializedName("criteria")
    private List<Map<String, Object>> criteria;
    private List<Criterion> criteriaList;

    public Criteria() {
        //нужна кастомная реализация criteria, чтобы сразу появлялся массив критериев
    }

    public List<Map<String, Object>> getCriteriaList() {
        return criteria;
    }

    public Criteria(List<Map<String, Object>> criteria) {
        this.criteria = criteria;
    }

    public List<Map<String, Object>> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Map<String, Object>> criteria) {
        this.criteria = criteria;
    }

    public Map<String, Object> getCriterion(int index) {
        return criteria.get(index);
    }

    public void setCriterion(int index, Map<String, Object> criterion) {
        this.criteria.set(index, criterion);
    }
}