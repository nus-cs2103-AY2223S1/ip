package duke.exception;

/**
 * Throws an exception when description is provided but not needed.
 */
public class NoDescriptionException extends DukeException {
    /**
     * Throws an error message indicating that the given command do not need a description.
     *
     * @param command command that does not require description.
     */
    public NoDescriptionException(String command) {
        super("We expect no other description after " + command + " command!");
    }
}
