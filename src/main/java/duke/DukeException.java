package duke;

/**
 * Encapsulates custom Duke related exceptions thrown when using duke.
 */
public class DukeException extends RuntimeException {
    DukeException(String message) {
        super(message);
    }

    DukeException() {
        super();
    }
}
