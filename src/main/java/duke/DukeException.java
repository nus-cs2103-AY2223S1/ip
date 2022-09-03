package duke;

/** A class to represent the exceptions thrown when using the bot. */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "oops, Duke Exception detected";
    }
}
