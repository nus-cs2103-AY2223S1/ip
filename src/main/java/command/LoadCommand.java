package command;

import exception.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class LoadCommand extends Command{
    
    public LoadCommand() {
        super();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            storage.loadLog();
        } catch (DukeException e) {
            throw e;
        }
    }

    @Override
    public Task getTask() {
        return Task.empty();
    }
}
