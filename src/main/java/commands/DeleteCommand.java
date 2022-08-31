package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.InvalidIndexException;
/**
 * Represents a command to delete a task to the list.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Creates a DeleteCommand with the index of the Task to be deleted.
     *
     * @param index the index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        tasks.delete(this.index);
    }

    public boolean isExit() {
        return false;
    }
}
