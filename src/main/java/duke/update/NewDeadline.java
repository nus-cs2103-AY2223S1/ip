package duke.update;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Represents a NewDeadline.
 */
public class NewDeadline extends NewTask {
    private static final String DELIMITER = " /by ";
    private String[] newTaskArray;

    /**
     * Creates a NewDeadline instance.
     * @param newTaskArray The array that represents the Deadline.
     */
    public NewDeadline(String[] newTaskArray) {
        this.newTaskArray = newTaskArray;
    }

    /**
     * Create a new Deadline.
     * @return The Deadline created.
     * @throws DukeException If the Deadline cannot be created.
     */
    @Override
    public Task create() throws DukeException {
        if (this.newTaskArray.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty!");
        }

        // split again to get date/time
        String[] splitArray = this.newTaskArray[1].split(NewDeadline.DELIMITER, 2);

        if (splitArray.length < 2) {
            throw new DukeException("Please enter a due date for this task!");
        }

        // Make a new deadline object
        Deadline deadline = new Deadline(splitArray[0], splitArray[1]);
        return deadline;
    }
}
