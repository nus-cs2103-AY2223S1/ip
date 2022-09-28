package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents the list of task in the task list
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        return ui.displayMessage(taskList.taskListString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}