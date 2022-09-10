package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class MarkCommand extends Command{
    
    public MarkCommand(String commandArgs) {
        super(commandArgs);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int listPos = Integer.parseInt(commandArgs);
        tasks.mark(listPos);
        ui.mark(listPos);
    }

    @Override
    public Task getTask() {
        return null;
    }
}
