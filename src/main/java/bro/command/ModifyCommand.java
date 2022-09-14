package bro.command;

import java.io.IOException;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;

/**
 * ModifyCommand Class.
 */
public class ModifyCommand extends Command {
    /**
     * An enum for status.
     */
    public enum ModifyType {
        MARK, UNMARK
    }

    private ModifyType status;
    private int index;

    /**
     * Constructor of the ModifyCommand class.
     * @param status Specifies the type of the modification to be done.
     * @param index Gives the index value to the index variable.
     */
    public ModifyCommand(ModifyType status, int index) {
        this.status = status;
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * Marks or unmarks the task.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws BroException {
        switch (status) {
        case MARK:
            try {
                tasklist.markTask(this.index);
                storage.modifyTaskFile(tasklist);
            } catch (IndexOutOfBoundsException e) {
                throw new BroException("Index is out of bound. Enter a valid index");
            } catch (IOException e) {
                throw new BroException("Couldn't delete task!");
            }
            return ui.markUi(tasklist, index) + ui.listSize(tasklist);
        case UNMARK:
            try {
                tasklist.unmarkTask(this.index);
                storage.modifyTaskFile(tasklist);
            } catch (IndexOutOfBoundsException e) {
                throw new BroException("Index is out of bound. Enter a valid index");
            } catch (IOException e) {
                throw new BroException("Couldn't delete task!");
            }
            return ui.unmarkUi(tasklist, index) + ui.listSize(tasklist);
        default:
            return "Exception";
        }
    }
}
