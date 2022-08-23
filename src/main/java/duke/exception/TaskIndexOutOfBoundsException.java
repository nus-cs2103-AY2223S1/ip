package duke.exception;

/**
 * Represents the exception which occurs when the user tries to excess a <code>Task</code> object 
 * from a <code>TaskList</code> via its <code>taskIndex</code>, but the <code>Task</code> object cannot be found.
 * 
 * @author njxue
 * @version v0.1
 */
public class TaskIndexOutOfBoundsException extends DukeException {
    /**
     * Creates a <code>TaskOutOfBoundsException</code>.
     * 
     * @param providedIndex Incorrect task index provided by the user.
     * @param maxIndex Maximum valid task index, which is also the size of the current <code>TaskList</code>.
     */
    public TaskIndexOutOfBoundsException(int providedIndex, int maxIndex) {
        super(maxIndex == 0 ? "You have no tasks!"
                : "The provided task index " + providedIndex
                + " is out of range! Accepted Range: [1, " + maxIndex + "]");
    }
}
