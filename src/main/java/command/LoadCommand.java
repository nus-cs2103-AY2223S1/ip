package main.java.command;

import main.java.exception.DukeException;
import main.java.main.Storage;
import main.java.main.TaskList;
import main.java.main.Ui;
import main.java.task.Task;

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
