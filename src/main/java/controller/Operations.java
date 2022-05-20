package controller;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Operations {
    private final List<Map<String, ?>> criteria;
    private Statement statement;

    public Operations(List<Map<String, ?>> criteria, Statement statement) {
        this.criteria = criteria;
        this.statement = statement;
    }

    public void doSearch() throws SQLException {
        List<String> queries = new ArrayList<>();
        for (Map<String, ?> criterion : criteria) {
            //Поиск покупателей по фамилии
            if (criterion.containsKey("lastName")) {
                String lastName = (String)criterion.get("lastName");
                String query = "SELECT customers.name, \"lastName\" " +
                        "FROM customers " +
                        "WHERE \"lastName\" = '" + lastName + "';";
                queries.add(query);
            }

            //Поиск покупателей, купивших этот товар не менее, чем указанное число раз
            if (criterion.containsKey("productName")) {
                //TODO
            }

            //Поиск покупателей, у которых общая стоимость всех покупок за всё время попадает в интервал
            if (criterion.containsKey("minExpenses")) {
                //TODO
            }

            //Поиск покупателей, купивших меньше всего товаров. Возвращается не более, чем указанное число покупателей
            if (criterion.containsKey("badCustomers")) {
                //TODO
            }
        }
        if (queries.isEmpty()) {
            return;
        }

        for (String query : queries) {
            statement.executeQuery(query);
        }
    }

    public void doStat() {

    }
}