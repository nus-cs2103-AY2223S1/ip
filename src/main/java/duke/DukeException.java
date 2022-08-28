package duke;

/**
 * Represents an exception that occurs in Duke.
 */
public class DukeException extends Exception{
    private final String MESSAGE;

    public DukeException(String message) {
        super(message);
        this.MESSAGE = message;
    }

    @Override
    public String toString() {
        return MESSAGE;
    }
}
