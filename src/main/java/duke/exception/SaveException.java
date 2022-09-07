package duke.exception;

/**
 * A class representing a problem while saving tasks
 *     exception.
 */
public class SaveException extends DukeException {
    public SaveException() {
        super("There is a problem with saving the tasks");
    }
}
