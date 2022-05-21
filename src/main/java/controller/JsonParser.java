package controller;

import com.google.gson.Gson;
import model.Criteria;

import java.io.FileReader;

public class JsonParser {
    public Criteria parse(String fileName) {
        Gson gson = new Gson();

        try(FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, Criteria.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}