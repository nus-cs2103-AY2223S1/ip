package duke.exception;

/**
 * Throws an exception when an index is expected but not given.
 */
public class IndexNotSpecifyException extends DukeException {
    /**
     * Throws an error message when index is not given.
     *
     * @param command command which requires an index.
     */
    public IndexNotSpecifyException(String command) {
        super("We expects an index to execute " + command + " command!");
    }
}
