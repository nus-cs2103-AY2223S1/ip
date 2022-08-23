package duke.command;

import duke.DukeException;
import duke.Task;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        taskList.addTask(task);
        storage.saveFile(taskList);
        String message = "Got it. I've added this task:\n"
                + "\t" + task
                + taskList.tasksLeft();
        ui.formatMessage(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
