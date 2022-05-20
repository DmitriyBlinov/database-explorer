package controller;

import com.google.gson.Gson;
import model.Root;

import java.io.FileReader;

public class JsonParser {
    public Root parse(String fileName) {
        Gson gson = new Gson();

        try(FileReader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, Root.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}