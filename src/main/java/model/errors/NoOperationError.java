package model.errors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.FileWriter;
import java.io.Writer;

public class NoOperationError implements Error {
    @SerializedName("type")
    private String type = "error";
    @SerializedName("message")
    private final String message = "No operation specified";

    public NoOperationError() {

    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public void writeError() {
        try {
            Writer writer = new FileWriter("output.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(this, writer);
            String json = gson.toJson(this);
            writer.flush();
            writer.close();
            System.out.println(json);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
