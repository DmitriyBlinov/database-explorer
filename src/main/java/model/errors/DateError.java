package model.errors;

import com.google.gson.annotations.SerializedName;
import model.errors.Error;

public class DateError implements Error {
    @SerializedName("type")
    private String type = "error";
    @SerializedName("message")
    private final String message = "Wrong date format";

    public DateError() {

    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}