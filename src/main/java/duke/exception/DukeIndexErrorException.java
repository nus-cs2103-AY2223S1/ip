package duke.exception;


/**
 * Thrown to indicate that the index provided to the TaskList is out of range.
 *
 * @author Justin Peng
 */
public class DukeIndexErrorException extends DukeException {
    /**
     * Constructs a {@code DukeIndexErrorException} with a message if the user has no tasks.
     */
    public DukeIndexErrorException() {
        super(String.format("☹ OOPS!!! Please add a task to use this command."));
    }

    /**
     * Constructs a {@code DukeIndexErrorException} with a message showing the current number of tasks.
     *
     * @param size The current number of tasks.
     */
    public DukeIndexErrorException(int size) {
        super(String.format("☹ OOPS!!! Choose a task number from 1-%d.", size));
    }
}
