package model.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Response {
    private List<Map<String, Object>> data;

    public Response() {
        data = new ArrayList<>();
    }

    public void addResponseData(Map<String, Object> criterion) {
        data.add(criterion);
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }
}
