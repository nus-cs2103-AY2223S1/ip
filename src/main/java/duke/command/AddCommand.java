package duke.command;

import duke.model.TaskList;
import duke.model.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddCommand extends Command {

    private Task createdTask;
    public AddCommand(Task task) {
        createdTask = task;
    }
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.add(createdTask);
        storage.save(taskList);
        ui.add(createdTask);
    }
}
