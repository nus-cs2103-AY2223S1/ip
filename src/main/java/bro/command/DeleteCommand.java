package bro.command;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;

/**
 * DeleteCommand class.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor of the DeleteCommand class.
     * @param index Gives the integer value to the given index variable.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * Deletes the given task by the user.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws BroException {
        try {
            return tasklist.deleteTask(this.index, storage);
        } catch (IndexOutOfBoundsException e) {
            throw new BroException("Index is out of bound. Enter a valid index");
        }
    }
}
