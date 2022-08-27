package cheese.command;

import cheese.storage.Storage;
import cheese.ui.Ui;
import cheese.data.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.showTaskList(taskList);
    }
}
