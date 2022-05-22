package controller.json;

import com.google.gson.Gson;
import model.json.JsonCriteria;

import java.io.FileReader;

public class JsonSearchParser {
    public JsonCriteria parse(String fileName) {
        Gson gson = new Gson();

        try(FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, JsonCriteria.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}