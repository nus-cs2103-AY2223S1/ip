package yilia.command;

import yilia.Storage;
import yilia.Ui;
import yilia.task.TaskList;

public class ExitCommand extends Command{
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showBye();
    }
}