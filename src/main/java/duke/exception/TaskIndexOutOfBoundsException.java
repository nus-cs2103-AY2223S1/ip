package duke.exception;

/**
 * Represents the exception which occurs when the user tries to excess a Task object from a TaskList via its taskIndex,
 * but the Task object cannot be found.
 *
 * @author njxue
 * @version v0.1
 */
public class TaskIndexOutOfBoundsException extends DukeException {
    /**
     * Creates a TaskOutOfBoundsException.
     *
     * @param providedIndex Incorrect task index provided by the user.
     * @param maxIndex Maximum valid task index, which is also the size of the current TaskList.
     */
    public TaskIndexOutOfBoundsException(int providedIndex, int maxIndex) {
        super(maxIndex == 0 ? "You have no tasks!"
                : "The provided task index " + providedIndex
                + " is out of range! Accepted Range: [1, " + maxIndex + "]");
    }
}
