package commands;

import byu.*;
import exceptions.InvalidIndex;

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

    public void execute(ToDoList list, Ui ui) throws InvalidIndex {
        list.unmark(this.index);
    }

    public boolean isExit() {
        return false;
    }

}
