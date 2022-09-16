package command;

import exception.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class ByeCommand extends Command{
    
    public ByeCommand() {
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        return;
    }

    public Task getTask() {
        return Task.empty();
    }

}
