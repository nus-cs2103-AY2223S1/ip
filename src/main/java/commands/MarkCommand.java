package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.InvalidIndexException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {

    private int index;

    /**
     * Creates a MarkCommand with the index of the Task to be marked as done.
     *
     * @param index the index of the Task to be deleted.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        tasks.mark(this.index);
    }

    public boolean isExit() {
        return false;
    }

}
