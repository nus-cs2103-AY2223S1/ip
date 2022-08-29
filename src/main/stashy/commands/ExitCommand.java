package main.stashy.commands;

import main.stashy.data.exception.StashyException;
import main.stashy.data.task.TaskList;
import main.stashy.storage.Storage;
import main.stashy.ui.Ui;

public class ExitCommand extends Command {
    public static final String KEYWORD = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StashyException {
        ui.sayGoodbye();
        storage.writeTaskListToFile(tasks);
    }
}
