package duke.command;

import duke.Storage;
import duke.exception.InvalidIndexException;
import duke.task.TaskList;

/**
 * Represents a Mark Command
 */
public class MarkCommand extends Command {
    private boolean isMark;
    private int index;

    /**
     * Creates a Mark Command object
     */
    public MarkCommand(boolean isMark, int index) throws InvalidIndexException {
        if (index < 0 || index > TaskList.length() - 1) {
            throw new InvalidIndexException();
        }
        this.isMark = isMark;
        this.index = index;
    }

    /**
     * Marks a task in tasklist as done/not done
     * saves tasklist to task file
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.markTask(this.isMark, this.index);
        storage.saveTaskFile(taskList);
    }
}
