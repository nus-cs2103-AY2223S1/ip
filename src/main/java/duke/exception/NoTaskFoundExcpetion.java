package duke.exception;

/**
 * Throws an exception TaskList is empty.
 */
public class NoTaskFoundExcpetion extends DukeException {
    /**
     * Throws an error message when TaskList is empty.
     */
    public NoTaskFoundExcpetion() {
        super("No task found!");
    }
}
