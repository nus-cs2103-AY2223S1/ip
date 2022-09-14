package duke.exception;

/**
 * An exception class that is thrown when a task command has an index argument, but out of bound.
 * For example, if there are 5 tasks in the list, then mark 6 will invoke this exception.
 */
public class DukeIndexOutOfBoundException extends DukeException {

    /**
     * The standard constructor.
     */
    public DukeIndexOutOfBoundException(String message) {
        super(message);
    }

}
