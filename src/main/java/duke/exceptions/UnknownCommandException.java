package duke.exceptions;

/**
 * The type Unknown command exception.
 */
public class UnknownCommandException extends DukeException {

    /**
     * Instantiates a new Unknown command exception.
     *
     * @param errorString
     *            the error string
     */
    public UnknownCommandException(String errorString) {
        super(errorString);
    }
}
