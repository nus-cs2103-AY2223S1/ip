package bro.command;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws BroException {
        try {
            tasklist.deleteTask(this.index, storage);
        } catch (IndexOutOfBoundsException e){
            throw new BroException("Index is out of bound. Enter a valid index");
        }
    }
}
