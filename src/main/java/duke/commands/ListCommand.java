package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {

        ui.displayMessage(taskList.taskListString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}