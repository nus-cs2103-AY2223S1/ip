package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ListTasksCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws NoSuchTaskException {
        ui.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            ui.showMessage(String.format("%d. %s", i + 1, taskList.get(i)));
        }
    }
}