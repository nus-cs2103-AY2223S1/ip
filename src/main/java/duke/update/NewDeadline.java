package duke.update;

import duke.command.DeadlineCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;

import java.util.Arrays;

public class DeadlineUpdate extends NewTask {
    private static final String DELIMITER = " /by ";
    private String[] newTaskArray;

    public DeadlineUpdate(String[] newTaskArray) {
        this.newTaskArray = newTaskArray;
    }

    @Override
    public Task create() throws DukeException {
        if (this.newTaskArray.length < 2) {
            throw new DukeException("The description of a deadline cannot be empty!");
        }

        // split again to get date/time
        String[] splitArray = this.newTaskArray[1].split(DeadlineUpdate.DELIMITER, 2);


        if (splitArray.length < 2) {
            throw new DukeException("Please enter a due date for this task!");
        }

        // Make a new deadline object
        Deadline deadline = new Deadline(splitArray[0], splitArray[1]);

        return deadline;
    }
}
