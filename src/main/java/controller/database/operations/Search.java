package controller.database.operations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import model.criteria.results.Results;
import model.criteria.criterion.Criterion;
import model.criteria.criterion.LastNameCriterion;
import model.errors.NoConditionsError;

import java.io.FileWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Search {
    @SerializedName("type")
    private final String type = "search";
    @SerializedName("results")
    private List<Results> mainResults;
    private transient final List<Map<String, Object>> criteria;
    private transient Statement statement;
    private transient Criterion criterion;
    private transient String fileName;

    public Search(List<Map<String, Object>> criteria, Statement statement, String fileName) {
        this.criteria = criteria;
        this.statement = statement;
        this.fileName = fileName;
    }

    public void search() throws SQLException {
        this.criterion = new LastNameCriterion();
        mainResults = new ArrayList<>();
        for (Map<String, Object> criterion : criteria) {
            this.criterion = this.criterion.getCriterionInstance(criterion);
            if (!Objects.equals(this.criterion, null)) {
                String query = this.criterion.prepareSearchQuery();
                Map<Object, Object> conditions = new HashMap<>(this.criterion.getCriterionConditions());
                executeQuery(query, conditions);
            }
        }
        writeToJson();
    }

    private void executeQuery(String query, Map<Object, Object> conditions) throws SQLException {
        if (conditions.isEmpty()) {
            NoConditionsError error = new NoConditionsError();
            error.writeError();
        }
        ResultSet resultSet = statement.executeQuery(query);
        List<Map<String, Object>> responses = new ArrayList<>();

        Results results = new Results();
        while (resultSet.next()) {
            Map<String, Object> tempResponse = new HashMap<>();

            ResultSetMetaData metaData = resultSet.getMetaData();
            String firstColumn = metaData.getColumnName(1);
            String secondColumn = metaData.getColumnName(2);

            tempResponse.put(firstColumn, resultSet.getObject(firstColumn));
            tempResponse.put(secondColumn, resultSet.getObject(secondColumn));

            Map<String, Object> tempResult = new HashMap<>(tempResponse);
            responses.add(tempResult);

        }
        results.setCriteriaConditions(conditions);
        results.setResults(responses);
        this.mainResults.add(results);
    }

    public void writeToJson() {
        try {
            Writer writer = new FileWriter(fileName);
            Gson gson = new GsonBuilder().create();
            gson.toJson(this, writer);
            System.out.print("Search is over! Output file name: " + fileName);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}