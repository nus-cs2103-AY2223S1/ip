package bro.command;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;

public class ModifyCommand extends Command {
    public enum ModifyType{
        MARK, UNMARK
    }

    private ModifyType modifyType;
    private int index;

    public ModifyCommand(ModifyType modifyType, int index) {
        this.modifyType = modifyType;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws BroException {
        switch (modifyType) {
        case MARK:
            try {
                tasklist.markTask(this.index, storage);
            } catch (IndexOutOfBoundsException e) {
                throw new BroException("Index is out of bound. Enter a valid index");
            }
            break;
        case UNMARK:
            try {
                tasklist.unmarkTask(this.index, storage);
            } catch (IndexOutOfBoundsException e) {
                throw new BroException("Index is out of bound. Enter a valid index");
            }
            break;
        }
    }
}
