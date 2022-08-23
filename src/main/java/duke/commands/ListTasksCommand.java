package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.List;

public class ListTasksCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)  {
        List<Task> tasks = taskList.getAll();
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            ui.showMessage(String.format("%d. %s", i + 1, tasks.get(i)));
        }
    }
}