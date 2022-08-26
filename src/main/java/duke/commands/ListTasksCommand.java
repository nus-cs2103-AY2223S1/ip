package duke.commands;

import java.util.List;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Displays the user's current tasks in a numbered list format.
 */
public class ListTasksCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getAll();
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            ui.showMessage(String.format("%d. %s", i + 1, tasks.get(i)));
        }
    }
}
