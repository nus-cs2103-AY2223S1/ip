package bro.command;

import bro.Storage;
import bro.TaskList;
import bro.Ui;

public class ExitCommand extends Command {

    public ExitCommand(){}

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.bye();
    }

    public boolean isExit(){
        return this.isExit = true;
    }
}
