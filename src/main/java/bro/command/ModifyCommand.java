package bro.command;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;

/**
 * ModifyCommand Class.
 */
public class ModifyCommand extends Command {
    /**
     * An enum for modifyType.
     */
    public enum ModifyType {
        MARK, UNMARK
    }

    private ModifyType modifyType;
    private int index;

    /**
     * Constructor of the ModifyCommand class.
     * @param modifyType Specifies the type of the modification to be done.
     * @param index Gives the index value to the index variable.
     */
    public ModifyCommand(ModifyType modifyType, int index) {
        this.modifyType = modifyType;
        this.index = index;
    }

    /**
     * {@inheritDoc}
     *
     * Marks or unmarks the task.
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws BroException {
        switch (modifyType) {
        case MARK:
            try {
                return tasklist.markTask(this.index, storage);
            } catch (IndexOutOfBoundsException e) {
                throw new BroException("Index is out of bound. Enter a valid index");
            }
        case UNMARK:
            try {
                return tasklist.unmarkTask(this.index, storage);
            } catch (IndexOutOfBoundsException e) {
                throw new BroException("Index is out of bound. Enter a valid index");
            }
        default:
            return "Exception";
        }
    }
}
