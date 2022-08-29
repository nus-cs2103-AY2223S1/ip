package duke.command;

import duke.Storage;
import duke.exception.InvalidIndexException;
import duke.task.TaskList;

/**
 * Represents a Delete Command
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a Delete Command object
     */
    public DeleteCommand(int index) throws InvalidIndexException {
        if (index < 0 || index > TaskList.length() - 1) {
            throw new InvalidIndexException();
        }
        this.index = index;

    }

    /**
     * Deletes a task from tasklist
     * saves updated tasklist to task file
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        taskList.removeTask(index);
        storage.saveTaskFile(taskList);
    }
}
