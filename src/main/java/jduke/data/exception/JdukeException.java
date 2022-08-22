package jduke.data.exception;

public class JdukeException extends Exception {
    public JdukeException(String message) {
        super(String.format("%s", message));
    }
}
