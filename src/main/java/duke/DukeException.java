package duke;

/**
 * Represents an error that might occur during the programs execution.
 */
public class DukeException extends RuntimeException {

    public DukeException(String message) {

        super(message);
    }

}