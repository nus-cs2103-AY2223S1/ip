package duke.core;

/**
 * A class used for raising exceptions in Duke for Duke to catch.
 */
public class DukeException extends RuntimeException {
    public DukeException(String message) {
        super(message);
    }
}
