import controller.database.DatabaseWorker;
import controller.json.JsonSearchParser;
import controller.json.JsonStatParser;
import controller.database.operations.Search;
import controller.database.operations.Stat;
import model.errors.NoCriteriaError;
import model.json.JsonCriteria;
import model.json.JsonDates;
import model.errors.NoOperationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(args));
        if (arguments.size() != 3) {
            System.out.printf("Incorrect number of arguments: %s" + ". Expected number of arguments: 3%n", arguments.size());
            return;
        }
        String operationType = arguments.get(0);
        String inputJson = arguments.get(1);
        String outputJson = arguments.get(2);
        System.out.printf("Operation: %s" + ", Input JSON: %s" + ", Output JSON: %s%n", operationType, inputJson, outputJson);

        DatabaseWorker databaseWorker = new DatabaseWorker();
        if (!databaseWorker.getConnection()) {
            System.out.println("Connection was not set properly");
            return;
        }

        if (operationType.equals("search")) {
            JsonSearchParser parser = new JsonSearchParser();
            JsonCriteria jsonCriteria = parser.parse(inputJson);
            if (Objects.equals(jsonCriteria.getCriterion(0), null)) {
                NoCriteriaError noCriteriaError = new NoCriteriaError();
                noCriteriaError.writeError();
                return;
            }
            Search search = new Search(jsonCriteria.getCriteriaList(), databaseWorker.getStatement(), outputJson);
            try {
                search.search();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (operationType.equals("stat")) {
            JsonStatParser parser = new JsonStatParser();
            JsonDates dates = parser.parse(inputJson);
            Stat stat = new Stat(dates.getStartDate(), dates.getEndDate(), databaseWorker.getStatement(), outputJson);
            try {
                stat.requestStat();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            NoOperationError error = new NoOperationError();
            error.writeError();
        }
    }
}