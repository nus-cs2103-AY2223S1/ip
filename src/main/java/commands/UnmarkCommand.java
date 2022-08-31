package commands;

import byu.TaskList;
import byu.Ui;

import exceptions.InvalidIndexException;

/**
 * Represents a command to mark a task as undone.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Creates an UnmarkCommand with the index of the Task to be marked as undone.
     *
     * @param index the index of the Task to be marked as undone.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        tasks.unmark(this.index);
    }

    public boolean isExit() {
        return false;
    }

}
