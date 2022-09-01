package bro.command;

import bro.BroException;
import bro.Storage;
import bro.TaskList;
import bro.Ui;

public abstract class Command {
    boolean isExit = false;

    public boolean isExit(){
        return this.isExit;
    }

    public abstract void execute(TaskList tasklist, Ui ui, Storage storage) throws BroException;
}
