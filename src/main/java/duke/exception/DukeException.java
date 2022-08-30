package duke.exception;

/**
 * Throws an exception when duke requirements is not statisfied.
 */
public abstract class DukeException extends Exception {
    protected DukeException(String message) {
        super("OOPS!!! " + message);
    }
}
