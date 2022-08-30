package stashy.commands;

import stashy.data.exception.StashyException;
import stashy.data.task.TaskList;
import stashy.storage.Storage;
import stashy.ui.Ui;

public class ListCommand extends Command {
    public static final String KEYWORD = "list";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        ui.showTasks(tasks);
    }
}
