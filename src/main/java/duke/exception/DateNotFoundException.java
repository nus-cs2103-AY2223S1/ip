package duke.exception;

public class DateNotFoundException extends Exception {
    public DateNotFoundException(String message) {
        super("Date cannot be found in: " + message);
    }
}
