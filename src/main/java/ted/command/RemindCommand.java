package ted.command;

import ted.storage.Storage;
import ted.task.TaskList;
import ted.ui.Ui;

public class RemindCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage st) {
        return ui.remindResponse(tasks.getRemindTasks());
    }
}
