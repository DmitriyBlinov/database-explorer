package model.errors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Objects;

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
            Writer writer = new FileWriter("error.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(this, writer);
            System.out.print("Error! Error details were written to the file: error.json");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean checkForError(String... strings) {
        for (String s : strings) {
            if (Objects.equals(s, null)) {
                return true;
            }
        }
        return false;
    }
}
