package duke.exceptions;

/**
 * The type Missing argument exception.
 */
public class MissingArgumentException extends DukeException {

    /**
     * Instantiates a new Missing argument exception.
     *
     * @param errorString
     *            the error string
     */
    public MissingArgumentException(String errorString) {
        super(errorString);
    }
}
