package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

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
