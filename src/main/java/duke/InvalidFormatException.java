package duke;

/**
 * Exception that is thrown when format of date or time is incorrect.
 */
public class InvalidFormatException extends DukeException {
    public InvalidFormatException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! Please enter the correct format (/by or /at)";
    }
}
