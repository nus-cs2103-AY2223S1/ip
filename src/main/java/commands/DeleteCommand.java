package commands;

import byu.ToDoList;
import byu.Ui;

import exceptions.InvalidIndex;
/**
 * Represents a command to delete a task to the list.
 */
public class DeleteCommand extends Command{

    private int index;

    /**
     * Creates a DeleteCommand with the index of the Task to be deleted.
     *
     * @param index the index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(ToDoList list, Ui ui) throws InvalidIndex {
        list.delete(this.index);
    }

    public boolean isExit() {
        return false;
    }
}
