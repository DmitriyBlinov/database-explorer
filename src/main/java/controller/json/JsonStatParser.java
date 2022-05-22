package controller.json;

import com.google.gson.Gson;

import java.io.FileReader;

public class JsonStatParser {
    public JsonDates parse(String fileName) {
        Gson gson = new Gson();

        try(FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, JsonDates.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
