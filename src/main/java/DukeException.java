package main.java;

public class DukeException extends Exception {

    private String message;
    public DukeException(String string) {
        super(string);
        message = string;
    }

    public String getMessage() {
        return message;
    }
}
