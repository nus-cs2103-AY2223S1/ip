package bro.command;

import bro.Storage;
import bro.TaskList;
import bro.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index){
        this.index = index;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        tasklist.deleteTask(this.index, storage);
    }
}
