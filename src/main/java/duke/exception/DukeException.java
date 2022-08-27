package duke.exception;

/**
 * Represents a DukeException unique to Duke
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
}
