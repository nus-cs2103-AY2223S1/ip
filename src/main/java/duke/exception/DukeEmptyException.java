package duke.exception;

/**
 * A DukeEmptyException is thrown when the user did not input required arguments for the specified command.
 */
public class DukeEmptyException extends DukeException {
    public DukeEmptyException(String task) {
        super(String.format("☹ OOPS!!! The description of a %s cannot be empty.", task));
    }
}
