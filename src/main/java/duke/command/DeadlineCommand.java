package duke.command;

import duke.Storage;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidTimeException;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * Represents a Deadline Command
 */
public class DeadlineCommand extends Command {
    String desc;
    String time;

    /**
     * Creates a Deadline Command object
     */
    public DeadlineCommand(String desc, String time) throws InvalidDescriptionException, InvalidTimeException {
        if (desc.isEmpty()) {
            throw new InvalidDescriptionException();
        }
        if (time.isEmpty()) {
            throw new InvalidTimeException();
        }

        this.desc = desc;
        this.time = time;
    }

    /**
     * Creates a Deadline object
     * adds it to tasklist object
     * saves the tasklist to task file
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Deadline deadline = new Deadline(this.desc, this.time);
        taskList.addTask(deadline);
        storage.saveTaskFile(taskList);
    }
}
