package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        taskList.addTask(task);
        storage.save(taskList);
        ui.addSuccess(task, taskList.numOfTask());
    }
}
