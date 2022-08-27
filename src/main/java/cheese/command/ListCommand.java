package cheese.command;

import cheese.data.TaskList;
import cheese.storage.Storage;
import cheese.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showTaskList(taskList);
    }
}
