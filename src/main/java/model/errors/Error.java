package model.errors;

public interface Error {
    String getMessage();
    void writeError();
    boolean checkForError(String... strings);
}
