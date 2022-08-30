package duke.exception;

/**
 * Throws an exception when description is expected but not given.
 */
public class DescriptionNotSpecifyException extends DukeException {
    /**
     * Throws an error message stating that description is required.
     *
     * @param command commands which requires a description.
     */
    public DescriptionNotSpecifyException(String command) {
        super("We expect some description to be specified after " + command + " command!\nIt should not be empty!");
    }
}
