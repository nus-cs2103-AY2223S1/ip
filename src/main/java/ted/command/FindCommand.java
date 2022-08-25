package ted.command;

import ted.storage.Storage;
import ted.task.TaskList;
import ted.ui.Ui;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage st) {
        ui.tedResponse(tasks.findTasks(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
