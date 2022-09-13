package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.InvalidIndexException;

/**
 * A command to delete a task in the list.
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Creates a DeleteCommand with the index of the Task to be deleted.
     *
     * @param index the index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        tasks.delete(this.index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
