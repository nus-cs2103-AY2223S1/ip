package commands;

import byu.*;
import exceptions.InvalidIndex;

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

    public void execute(ToDoList list, Ui ui) throws InvalidIndex {
        list.mark(this.index);
    }

    public boolean isExit() {
        return false;
    }

}
