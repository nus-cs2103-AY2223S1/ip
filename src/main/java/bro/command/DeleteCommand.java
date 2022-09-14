package bro.command;

import java.io.IOException;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;
import bro.task.Task;

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
            Task t = tasklist.get(index - 1);
            tasklist.deleteTask(this.index);
            storage.modifyTaskFile(tasklist);
            return ui.deleteUi(t) + ui.listSize(tasklist);
        } catch (IndexOutOfBoundsException e) {
            throw new BroException("Index is out of bound. Enter a valid index");
        } catch (IOException e) {
            throw new BroException("Couldn't delete task!");
        }
    }
}
