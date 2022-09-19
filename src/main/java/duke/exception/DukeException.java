package duke.exception;

/**
 * Class which manages exceptions thrown when running Duke program.
 */
public class DukeException extends RuntimeException {

    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
