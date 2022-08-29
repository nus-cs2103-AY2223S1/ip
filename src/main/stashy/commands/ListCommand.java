package main.stashy.commands;

import main.stashy.data.exception.StashyException;
import main.stashy.data.task.TaskList;
import main.stashy.storage.Storage;
import main.stashy.ui.Ui;

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
