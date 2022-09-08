package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;

public class ExitCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {}

    @Override
    public boolean isExit() {
        return true;
    }
    
}
