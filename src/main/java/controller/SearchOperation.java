package controller;

import data.criteria.HowManyBoughtCriterion;
import data.criteria.Criterion;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class SearchOperation {
    private final List<Map<String, Object>> criteria;
    private Statement statement;
    private Criterion criterion;


    public SearchOperation(List<Map<String, Object>> criteria, Statement statement) {
        this.criteria = criteria;
        this.statement = statement;
        criterion = new HowManyBoughtCriterion();
    }

    public void doSearch() throws SQLException {
        List<String> queries = new ArrayList<>();
        for (Map<String, Object> criterion : criteria) {
            this.criterion = this.criterion.getCriterionInstance(criterion);
            if (!Objects.equals(this.criterion, null)) {
                String query = this.criterion.prepareSearchQuery();
                queries.add(query);
            }
        }
        if (queries.isEmpty()) {
            return;
        }

        ResultSet resultSet;
        for (String query : queries) {
            resultSet = statement.executeQuery(query);
            printResultSet(resultSet);
        }
    }

    private void printResultSet(ResultSet resultSet) {
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(metaData.getColumnName(i) + ": " + columnValue);
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void doStat() {

    }
}