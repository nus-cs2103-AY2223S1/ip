package chacha.commands;

import java.util.ArrayList;

import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

public class ExitCommand extends Command {
    
    @Override
    public void execute(TaskList taskList, Ui ui) {}

    @Override
    public boolean isExit() {
        return true;
    }
    
}
