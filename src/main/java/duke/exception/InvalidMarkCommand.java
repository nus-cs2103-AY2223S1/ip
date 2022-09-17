package duke.exception;

/**
 * An exception class that encapsulates the possible error could occur for marking a task.
 */
public class InvalidMarkCommand extends DukeException {
    public InvalidMarkCommand(String errorMessage) {
        super(errorMessage);
    }
}
