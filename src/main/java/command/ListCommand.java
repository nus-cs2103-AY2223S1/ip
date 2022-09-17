package main.java.command;

import main.java.main.Storage;
import main.java.main.TaskList;
import main.java.main.Ui;
import main.java.task.Task;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks);

    }

    @Override
    public Task getTask() {
        return Task.empty();
    }
}
