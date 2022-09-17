package main.java.command;

import main.java.exception.DukeException;
import main.java.main.Storage;
import main.java.main.TaskList;
import main.java.main.Ui;
import main.java.task.Task;

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
