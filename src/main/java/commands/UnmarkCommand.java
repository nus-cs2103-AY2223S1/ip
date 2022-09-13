package commands;

import byu.TaskList;
import byu.Ui;
import exceptions.InvalidIndexException;

/**
 * A command to mark a task as incomplete.
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Creates an UnmarkCommand with the index of the Task to be marked as incomplete.
     *
     * @param index the index of the Task to be marked as incomplete.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws InvalidIndexException {
        tasks.unmark(this.index);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
