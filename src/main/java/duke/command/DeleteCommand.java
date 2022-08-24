package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        if (index >= taskList.numberOfTasks()) {
            throw new InvalidArgumentException(Commands.Delete);
        }
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        storage.saveFile(taskList);
        String message = "Noted. I've removed this task:\n" + task + taskList.tasksLeft();
        ui.formatMessage(message);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
