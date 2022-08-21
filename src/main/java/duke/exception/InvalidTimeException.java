package duke.exception;

/**
 * Represents an exception when input time is unable to be parsed
 */
public class InvalidTimeException extends Exception {
    public InvalidTimeException() {
        super("____________________________________________________________\n" +
                "Oh no! This command requires a time input!\n" +
                "____________________________________________________________");
    }
}
