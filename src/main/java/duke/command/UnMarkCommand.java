package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnMarkCommand extends Command {

    private int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        if (index >= taskList.numberOfTasks()) {
            throw new InvalidArgumentException(Commands.Mark);
        }
        Task task = taskList.getTask(index);
        task.markAsIncomplete();
        storage.saveFile(taskList);
        ui.formatMessage("Nice! I've marked this task as not done yet:\n" + task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
