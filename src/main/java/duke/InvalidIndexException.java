package duke;

/**
 * InvalidIndexException is an exception that is thrown when user enter invalid input.
 */
public class InvalidIndexException extends DukeException {

    public InvalidIndexException() {
        super("OOPS!!! Invalid index, please enter a valid task index.");
    }
}
