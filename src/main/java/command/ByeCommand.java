package command;

import tasklist.TaskList;
import utility.Storage;
import utility.Ui;


public class ByeCommand extends Command {
    
    public ByeCommand() {
        super("bye");
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
